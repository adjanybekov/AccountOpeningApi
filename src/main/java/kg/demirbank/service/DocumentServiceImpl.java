package kg.demirbank.service;

import kg.demirbank.model.FileInfo;
import kg.demirbank.model.documentManagementApi.FileCopyDto;
import kg.demirbank.model.documentManagementApi.FileMetaDto;
import kg.demirbank.proxy.ReportApiProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("DocumentService")
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    ReportApiProxy reportProxy;

    @Value("${dmsApi}")
    private String dmsApi;

    @Override
    public Map<String, Object> uploadFile(MultipartFile file, HashMap<String,Object> map){

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        Map<String, Object> responseBody = new HashMap<>();

        String customerNo = map.get("customerNo").toString();
        String nameOfSpec = map.get("nameOfSpec").toString().toUpperCase();
        List<String> tags = (List<String>) map.get("tags");

        if(!file.isEmpty()){
            body.add("file", file.getResource());
        }

        body.add("tags",tags);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        String url =String.format(dmsApi+"/uploadFile/%s",customerNo);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
            responseBody = responseEntity.getBody();

            Map<String,Object> userMap = getUserInfoById(nameOfSpec.toUpperCase());
            String query = String.format("insert into webportal.signature2 (CUST_NO, file_id,user_id,user_branch) " +
                            "values(%s, %s, '%s', '%s')",
                    customerNo,responseBody.get("id"),userMap.get("KODU"), userMap.get("CALISILAN_BOLUM"));

            boolean done  = reportProxy.executeQuery(query);
            responseBody.put("custNo",customerNo);
            responseBody.put("userMap",userMap);
        }

        return responseBody;
    }

    @Override
    public  Map<String, String> uploadFileToTmp(MultipartFile file){

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String,Object> body = new LinkedMultiValueMap();
        body.add("file",file.getResource());
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(body,headers);
        Map<String, String> response = restTemplate.postForObject(dmsApi+"/uploadFileToTmp",requestEntity,Map.class);

        return response;
    }

    @Override
    public Map<String, Object> copyFileFromTmpToFolderById(Map<String,Object> map){

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> responseBody=new HashMap<>();

        String customerNo = map.get("customerNo").toString();
        String nameOfSpec = map.get("nameOfSpec").toString().toUpperCase();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        String query = String.format("select * from webportal.hierarchy where id = '%s'",map.getOrDefault("fileTag","").toString());

        List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);

        //construct name of file
        String fileName = result.get(0).get("NAME").toString() + ((map.get("name")==null)?"":"_"+map.get("name"));



        String url =String.format(dmsApi+"/copyFileFromTmpToFolderById/%s/%s",map.get("fileId"),fileName);

        Map<String,Object> body = new HashMap<>();
        List<String> tags = new ArrayList<>();
        String[] tagsArr = map.getOrDefault("fileTag","").toString().split("/");
        tags =Arrays.asList(tagsArr);


        body.put("tags",tags);//change to last TagId
//        body.put("name",map.get("name"));//change to last TagId+name if exists
        body.put("path",customerNo+"/"+map.getOrDefault("fileTag",""));


        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity(body, headers);

        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        if(responseEntity.getStatusCode().equals(HttpStatus.OK)){

            responseBody = responseEntity.getBody();

            Map<String,Object> userMap = getUserInfoById(nameOfSpec.toUpperCase());
            query = String.format("insert into webportal.signature2 (CUST_NO, file_id,user_id,user_branch) " +
                            "values(%s, %s, '%s', '%s')",
                    customerNo,responseBody.get("id"), userMap.get("KODU"), userMap.get("CALISILAN_BOLUM"));

            boolean done  = reportProxy.executeQuery(query);
            responseBody.put("custNo",customerNo);
            responseBody.put("userMap",userMap);
        }

        return responseBody;
    }

    @Override
    public Map<String, Object> viewFileById(Integer id){

        RestTemplate restTemplate = new RestTemplate();
        String viewUrl = dmsApi+"/viewFileById/"+id;
        byte[]  media =  restTemplate.getForObject(viewUrl,byte[].class);
        String getUrl = dmsApi+"/getFilesByIds";
        List<Integer> l = new ArrayList<>();
        l.add(id);
        List<Map<String,Object>> listObject =  restTemplate.postForObject(getUrl,l, List.class);
        String extension = listObject.get(0).get("extension").toString();

        Map<String,Object> response = new HashMap<>();
        response.put("bytes",Base64.getEncoder().encodeToString(media));
        response.put("type",extensionToType(extension));
        return response;
    }

    private String extensionToType(String extension) {
        String[] imageTypes = {"jpg","jpeg","bmp","gif","png"};
        if(extension.equals("pdf")){
            return "pdf";
        }else if(Arrays.asList(imageTypes).contains(extension)){
            return  "image";
        }
        return null;
    }

    @Override
    public List<FileInfo> getFilesByCustomerId(String customerNo) {

        List<Map<String, Object>> signatureResult =  getFilesSignature(customerNo);

        List<String> fileIds = new ArrayList<>();
        signatureResult.stream().forEach(a -> fileIds.add(a.get("FILE_ID").toString()));

        List<Map<String,Object>> filesResult = getFileInfo(customerNo,fileIds);

        Map<String,Map> signatureResultMap = new HashMap<>();
        signatureResult.stream().forEach(signature -> signatureResultMap.put(signature.get("FILE_ID").toString(),signature));

        List<FileInfo> mergeResult = new ArrayList<>();

        filesResult.stream().forEach(file -> {
            try {
                mergeResult.add( generateFileInfo(file,signatureResultMap.get(file.get("id").toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        return mergeResult;

    }

    @Override
    public Map<String,Object> getUserInfoById(String kodu){

        String query = String.format("select * from cbs.cbs_kullanici where kodu = '%s'",kodu);

        List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);

        return result.get(0);
    }

    @Override
    public Map<String,Object> addTagToFileByTagId(String kodu){

        String query = String.format("select * from cbs.cbs_kullanici where kodu = '%s'",kodu);

        List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);

        return result.get(0);
    }


    private FileInfo generateFileInfo(Map<String,Object> file, Map<String,Object> signature) throws ParseException {
        FileInfo fileInfo = new FileInfo();

        fileInfo.setCreatedDate( (new Date(Long.parseLong(file.get("createdDate").toString()) ) ));
        fileInfo.setFileId(Integer.parseInt(file.get("id").toString()));
        fileInfo.setFileName(file.get("nameWithExtension").toString());
//        fileInfo.setDownloadLink(file.get("downloadUri").toString());
        fileInfo.setFileType(generateFileTags(((List<Map>)file.get("tags")).get(0).get("tagName").toString()));
        fileInfo.setFileId( Integer.parseInt(file.get("id").toString()));

        fileInfo.setCustNo(signature.get("CUST_NO").toString());
        fileInfo.setUserId(signature.get("KODU").toString());
        fileInfo.setUserName(signature.get("ADI").toString()+" "+signature.get("SOYADI").toString());
        fileInfo.setUserBranch(signature.get("CALISILAN_BOLUM").toString());
        fileInfo.setUserBranchName(signature.get("CALISILAN_BOLUM_ADI").toString());

        return  fileInfo;
    }

    private List<Map<String, Object>> getFileInfo(String customerNo, List<String> fileIds) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<List<String>> requestEntity = new HttpEntity<>(fileIds, headers);
        String url = String.format(dmsApi+"/getFilesByIds");
        List<Map<String,Object>>  resultList = restTemplate.postForObject(url,requestEntity,List.class);

        //sortTags(resultList);

        return resultList;
    }


    private List<Map<String, Object>> getFilesSignature(String customerNo) {

        String query = String.format("select a.*,b.*,c.adi calisilan_bolum_adi from webportal.signature2 a, cbs_kullanici b, CBS_BOLUM c where cust_no = '%s' and b.kodu = a.user_id and B.CALISILAN_BOLUM = C.KODU ",customerNo);

        List<Map<String, Object>> signatureResult = reportProxy.getReportSQLResult(query);
        return signatureResult;
    }


    @Override
    public List<Map<String,Object>> getTagsTree() {

        String query = String.format("select * from webportal.hierarchy");

        List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);

        List<Map<String,Object>> tags = new ArrayList<>();
        int n=result.size();
        for(int i=0; i<n; i++){

            if(result.get(i).get("PARENT_ID")==null){
                Map<String,Object> map = new HashMap<>();
                map.put("name",result.get(i).get("NAME").toString());
                map.put("id",result.get(i).get("ID").toString());
                map.put("shortname",result.get(i).get("NAME").toString());
                map.put("menu",new ArrayList<>());
                for (int j=0; j<n; j++){
                    if(result.get(j).getOrDefault("PARENT_ID",null)!=null && result.get(j).get("PARENT_ID").toString().equals(result.get(i).get("ID").toString())){
                        Map<String,Object> map2 = new HashMap<>();
                        map2.put("name",result.get(j).get("NAME").toString());
                        map2.put("id",result.get(j).get("ID").toString());
                        map2.put("menu",new ArrayList<>());
                        for (int k=0; k<n; k++){
                            if( result.get(k).getOrDefault("PARENT_ID",null)!=null && result.get(k).get("PARENT_ID").toString().equals(result.get(j).get("ID").toString()) ){
                                Map<String,Object> map3 = new HashMap<>();
                                map3.put("name",result.get(k).get("NAME").toString());
                                map3.put("id",result.get(k).get("ID").toString());
                                ((List)map2.get("menu")).add(map3);
                            }
                        }
                        ((List)map.get("menu")).add(map2);
                    }
                }
                tags.add(map);
            }
        }
        return tags;
    }

    @Override
    public String generateFileTags(String tagId) {

        String query = String.format("select * from webportal.hierarchy");

        List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);

        //
        Map<String,Object> leaf;
        List<Map> orderedTags = new ArrayList<>();
        for (Map m : result){
            if(m.get("ID").toString().equals(tagId)){
                leaf = m;
                orderedTags.add(m);
                recurseBack(leaf,result,orderedTags);
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=orderedTags.size()-1; i>=0; i--){
            sb.append(String.format("/%s",orderedTags.get(i).get("NAME")));
        }

        return sb.toString();
    }

    private void recurseBack(Map<String, Object> leaf, List<Map<String, Object>> unorderedTags, List<Map> orderedTags) {
        if(leaf.get("PARENT_ID")!=null){
            for (Map<String,Object> m : unorderedTags){
                if(m.get("ID").equals(leaf.get("PARENT_ID"))){
                    orderedTags.add(m);
                    recurseBack(m,unorderedTags,orderedTags);
                    break;
                }
            }
        }
    }

    @Override
    public String deleteFileById(Integer id) {

//        RestTemplate restTemplate = new RestTemplate();
//
//        String deleteUrl = String.format(dmsApi+"/deleteFileById/%d",id);
//        restTemplate.delete(deleteUrl);

        String deleteSql = String.format("delete from webportal.signature2 where file_id = %d",id);
        boolean status = reportProxy.executeQuery(deleteSql);
        return status?"deleted successfully":"delete failed";
    }

    @Override
    public Map<String, Object> copyFile(FileMetaDto fileMetaDto) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> responseBody=new HashMap<>();

        String customerNo = fileMetaDto.getCustomerNo();
        String nameOfSpec = fileMetaDto.getNameOfSpec().toUpperCase();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        String query = String.format("select * from webportal.hierarchy where id = '%s'",fileMetaDto.getFileTag());
        List<Map<String, Object>> result = reportProxy.getReportSQLResult(query);

        //construct name of file
        String fileName = result.get(0).get("NAME").toString() + ((fileMetaDto.getName()==null)?"":"_"+fileMetaDto.getName());

        String url =String.format(dmsApi+"/copyFile");

        String[] tagsArr = fileMetaDto.getFileTag().split("/");
        List<String> tags = Arrays.asList(tagsArr);

        FileCopyDto fileCopyDto = new FileCopyDto()
                .setTags(tags)
                .setDestFile(fileName)
                .setDestFolder(String.format("%s/%s",customerNo,fileMetaDto.getFileTag()))
                .setSourceFile(fileMetaDto.getFileId());


        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity(fileCopyDto, headers);

        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        if(responseEntity.getStatusCode().equals(HttpStatus.OK)){

            responseBody = responseEntity.getBody();

            Map<String,Object> userMap = getUserInfoById(nameOfSpec.toUpperCase());
            query = String.format("insert into webportal.signature2 (CUST_NO, file_id,user_id,user_branch) " +
                            "values(%s, %s, '%s', '%s')",
                    customerNo,responseBody.get("id"), userMap.get("KODU"), userMap.get("CALISILAN_BOLUM"));

            boolean done  = reportProxy.executeQuery(query);
            responseBody.put("custNo",customerNo);
            responseBody.put("userMap",userMap);
        }

        return responseBody;
    }
}
