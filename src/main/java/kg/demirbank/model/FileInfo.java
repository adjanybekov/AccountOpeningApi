package kg.demirbank.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class FileInfo {
    //info from dms file_entity (created_date,fileName,downloadLink,fileId)

    //info from wp signature2 (userId,UserName,UserBranch,CustNo,FileId)

    private Integer fileId;
    private Date createdDate;
    private String fileName;
    private String downloadLink;
    private String userName;
    private String userBranch;
    private String userBranchName;
    private String userId;
    private String custNo;
    private String custName;
    private String fileType;


    public FileInfo() {
    }

    public FileInfo(Integer fileId, Date createdDate, String fileName, String downloadLink, String userName, String userBranch, String userId, String custNo, String custName) {
        this.fileId = fileId;
        this.createdDate = createdDate;
        this.fileName = fileName;
        this.downloadLink = downloadLink;
        this.userName = userName;
        this.userBranch = userBranch;
        this.userId = userId;
        this.custNo = custNo;
        this.custName = custName;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBranch() {
        return userBranch;
    }

    public void setUserBranch(String userBranch) {
        this.userBranch = userBranch;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUserBranchName() {
        return userBranchName;
    }

    public void setUserBranchName(String userBranchName) {
        this.userBranchName = userBranchName;
    }


}
