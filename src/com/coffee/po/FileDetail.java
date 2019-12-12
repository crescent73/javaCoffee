package com.coffee.po;

import java.util.List;

public class FileDetail extends File {
    private String teacherName;
    private List<Attachment> attachmentList;

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + getId() +
                ", fileName='" + getFileName() + '\'' +
                ", uploadDate=" + getUploadDate() +
                ", updateDate=" + getUpdateDate() +
                ", courseId=" + getCourseId() +
                ", uploaderId=" + getUploaderId() +
                ", fileDescription='" + getFileDescription() + '\'' +
                ", teacherName=" + teacherName +
                ", attachmentList=" + attachmentList +
                '}';
    }
}
