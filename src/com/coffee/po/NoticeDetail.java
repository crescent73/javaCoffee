package com.coffee.po;

public class NoticeDetail extends com.coffee.po.Notice {

    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + getId() +
                ", courseId=" + getCourseId() +
                ", publisherId=" + getPublisherId() +
                ", noticeLevel=" + getNoticeLevel() +
                ", noticeContent='" + getNoticeContent() + '\'' +
                ", publishDate=" + getPublishDate() +
                ", updateDate=" + getUpdateDate() +
                ", noticeTitle='" + getNoticeTitle() + '\'' +
                ", teacherName=" + teacherName +
                '}';
    }



}
