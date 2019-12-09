package com.murmur.po;

public class FileDetail extends File {
    private String teacherName;

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
                '}';
    }
}
