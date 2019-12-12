package com.coffee.po;

import java.lang.reflect.Field;
import java.sql.Timestamp;

public class Notice {
    private Long id;
    private Long courseId;
    private Long publisherId;
    private Integer noticeLevel;
    private String noticeContent;
    private Timestamp publishDate;
    private Timestamp updateDate;
    private String noticeTitle;

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

    public Timestamp getUpdateDate() {
        return updateDate;
    }



    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getNoticeLevel() {
        return noticeLevel;
    }

    public void setNoticeLevel(Integer noticeLevel) {
        this.noticeLevel = noticeLevel;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", publisherId=" + publisherId +
                ", noticeLevel=" + noticeLevel +
                ", noticeContent='" + noticeContent + '\'' +
                ", publishDate=" + publishDate +
                ", updateDate=" + updateDate +
                ", noticeTitle='" + noticeTitle + '\'' +
                '}';
    }
}
