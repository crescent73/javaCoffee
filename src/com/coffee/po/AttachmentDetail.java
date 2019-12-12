package com.coffee.po;

import java.io.File;

public class AttachmentDetail extends Attachment{
    private File file;

    public void setAttachmentInfo(Attachment attachment){
        setId(attachment.getId());
        setFileId(attachment.getFileId());
        setAttachmentName(attachment.getAttachmentName());
        setAttachmentPath(attachment.getAttachmentPath());
        setUpdateDate(attachment.getUpdateDate());
        setUploadDate(attachment.getUploadDate());
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "AttachmentDetail{" +
                "id=" + getId() +
                ", fileId=" + getFileId() +
                ", attachmentName='" + getAttachmentName() + '\'' +
                ", attachmentPath='" + getAttachmentPath() + '\'' +
                ", uploadDate=" + getUploadDate() +
                ", updateDate=" + getUpdateDate() +
                "file=" + file +
                '}';
    }
}
