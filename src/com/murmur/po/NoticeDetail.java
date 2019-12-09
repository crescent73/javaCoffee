package com.murmur.po;

public class NoticeDetail extends com.murmur.po.Notice {

    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
                ", teacher='" + teacher + '\'' +
                '}';
    }

}
