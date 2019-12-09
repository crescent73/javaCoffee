package com.murmur.po;

import java.sql.Timestamp;

public class Attachment {
    private Long id;
    private Long fileId;
    private String attachmentName;
    private String attachmentPath;
    private Timestamp uploadDate;
    private Timestamp updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", fileId=" + fileId +
                ", attachmentName='" + attachmentName + '\'' +
                ", attachmentPath='" + attachmentPath + '\'' +
                ", uploadDate=" + uploadDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
