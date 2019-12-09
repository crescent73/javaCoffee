package com.murmur.po;

import java.lang.reflect.Field;
import java.sql.Timestamp;

public class File {
    private Long id;
    private String fileName;
    private Timestamp uploadDate;
    private Timestamp updateDate;
    private Long courseId;
    private Long uploaderId;
    private String fileDescription;

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }

    public void reset() {
        Field[] fields = getClass().getDeclaredFields();
        for(Field field : fields) {
            try {
                field.set(this, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", uploadDate=" + uploadDate +
                ", updateDate=" + updateDate +
                ", courseId=" + courseId +
                ", uploaderId=" + uploaderId +
                ", fileDescription='" + fileDescription + '\'' +
                '}';
    }
}
