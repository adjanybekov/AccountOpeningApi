package kg.demirbank.model;

public class CustomerFile {


    private String fileName;
    private String createdDate;
    private String extension;
    private String branchCode;
    private String branchName;
    private String documentType;
    private String linkToPath;
    private String nameOfSpecialist;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getLinkToPath() {
        return linkToPath;
    }

    public void setLinkToPath(String linkToPath) {
        this.linkToPath = linkToPath;
    }

    public String getNameOfSpecialist() {
        return nameOfSpecialist;
    }

    public void setNameOfSpecialist(String nameOfSpecialist) {
        this.nameOfSpecialist = nameOfSpecialist;
    }
}
