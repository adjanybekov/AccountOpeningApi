package kg.demirbank.controller;

import kg.demirbank.model.FileInfo;
import kg.demirbank.model.documentManagementApi.FileCopyDto;
import kg.demirbank.model.documentManagementApi.FileMetaDto;
import kg.demirbank.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @PostMapping(value = {"/uploadFile/**"})
    public ResponseEntity uploadFile(@RequestPart("file") MultipartFile file,
                                     @RequestPart("nonfile") HashMap<String,Object> map,
                                     HttpServletRequest request){

        Map<String, Object> responseBody = new HashMap<>();
        HttpStatus httpStatus = HttpStatus.CREATED;
        String msg="Success";

        try {
           responseBody = documentService.uploadFile(file,map);
        } catch (HttpStatusCodeException e) {
            httpStatus = HttpStatus.valueOf(e.getStatusCode().value());
            msg = e.getMessage();
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            msg = e.getMessage();
        }
        responseBody.put("message",msg);

        if(responseBody==null){
            return new ResponseEntity(responseBody,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(responseBody, httpStatus);
    }

    @PostMapping(value = {"/uploadFileToTmp"})
    public ResponseEntity uploadFileToTmp(@RequestPart("file") MultipartFile file){

        Map<String, String> response = documentService.uploadFileToTmp(file);

        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping(value = {"/copyFileFromTmpToFolderById/{fileId}"},consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity copyFileFromTmpToFolderById(@PathVariable("fileId") String fileId, @RequestBody Map<String,Object> map){

        Map<String, Object> responseBody = new HashMap<>();
        HttpStatus httpStatus = HttpStatus.CREATED;

        String msg="Success";

        try {

            responseBody = documentService.copyFileFromTmpToFolderById(map);

        } catch (HttpStatusCodeException e) {
            httpStatus = HttpStatus.valueOf(e.getStatusCode().value());
            msg = e.getMessage();
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            msg = e.getMessage();
        }
        responseBody.put("message",msg);
        if(responseBody==null){
            return new ResponseEntity(responseBody,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(responseBody, httpStatus);
    }

    @PostMapping({"/uploadMultipleFiles/**"})
    public List<ResponseEntity> uploadMultipleFiles(@RequestPart("files") MultipartFile[] files, @RequestPart("nonfiles") HashMap<String,Object>[] map, HttpServletRequest request) {
        return IntStream
                .range(0, files.length)
                .mapToObj(i->uploadFile(files[i],map[i],request))
                .collect(Collectors.toList());
    }

    @GetMapping(value = {"/viewFileById/{id}"})
    public ResponseEntity<Map<String,Object>> viewFileById2(@PathVariable("id") Integer id){


        Map<String,Object> response =  documentService.viewFileById(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(response,headers,HttpStatus.OK);
    }

    //todo this is test version of method with dummy data, needs to be fed with real data must return tags too
    @GetMapping("/getFilesByCustomerId/{customerNo}")//or is it better to call by path name
    public ResponseEntity getFilesByCustomerId(@PathVariable("customerNo") String customerNo, HttpServletRequest request) {

        List<FileInfo> mergeResult = documentService.getFilesByCustomerId(customerNo);

        return new ResponseEntity(mergeResult,HttpStatus.OK);

    }

    @GetMapping(value = {"/getUserInfoById/{kodu}"})
    public Map<String,Object> getUserInfoById(@PathVariable("kodu") String kodu){

        Map<String,Object> userInfo = documentService.getUserInfoById(kodu);

        return userInfo;
    }


    @PostMapping(value = {"/addTagToFileByTagId/{fileId}/{tagId}"})
    public Map<String,Object> addTagToFileByTagId(@PathVariable("kodu") String kodu){

        Map<String,Object> result = documentService.addTagToFileByTagId(kodu);

        return result;
    }


    @PostMapping(value = {"/getTagsTree"})
    public ResponseEntity getTagsTree(){
        List<Map<String,Object>> result = documentService.getTagsTree();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(value = {"/generateFileTags/{tagId}"})
    public ResponseEntity generateFileTags(@PathVariable("tagId") String tagId){
        String result = documentService.generateFileTags(tagId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping(value = {"/deleteFileById/{id}"})
    public ResponseEntity deleteFileById(@PathVariable("id")Integer id){
        String result = documentService.deleteFileById(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping(name = "/copyFile")
    public void copyFile(@RequestBody FileMetaDto fileMetaDto){



        documentService.copyFile(fileMetaDto);


    }

}
