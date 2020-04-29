package kg.demirbank.service;

import kg.demirbank.model.FileInfo;
import kg.demirbank.model.documentManagementApi.FileMetaDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DocumentService {

    Map<String, Object> uploadFile(MultipartFile file, HashMap<String,Object> map);
    Map<String, String> uploadFileToTmp(MultipartFile file);
    Map<String, Object> copyFileFromTmpToFolderById(Map<String,Object> map);
    List<FileInfo> getFilesByCustomerId(String customerNo);
    Map<String,Object> getUserInfoById(String kodu);
    Map<String,Object> addTagToFileByTagId(String kodu);
    List<Map<String,Object>> getTagsTree();
    String generateFileTags(String tagId);
    Map<String, Object> viewFileById(Integer id);

    String deleteFileById(Integer id);

    Map<String, Object> copyFile(FileMetaDto fileMetaDto);
}
