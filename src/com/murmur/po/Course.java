package com.murmur.po;

import java.lang.reflect.Field;

public class Course {
    private Long id;
    private String courseName;
    private String courseNumber;
    private Integer courseSchool;
    private String courseSemester;
    private Integer courseType;
    private String courseDescription;
    private String courseTeacherDescription;
    private Float courseCredit;
    private Long teacherId;

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseTeacherDescription() {
        return courseTeacherDescription;
    }

    public void setCourseTeacherDescription(String courseTeacherDescription) {
        this.courseTeacherDescription = courseTeacherDescription;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Integer getCourseSchool() {
        return courseSchool;
    }

    public void setCourseSchool(Integer courseSchool) {
        this.courseSchool = courseSchool;
    }

    public String getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(String courseSemester) {
        this.courseSemester = courseSemester;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Float getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Float courseCredit) {
        this.courseCredit = courseCredit;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", courseSchool=" + courseSchool +
                ", courseSemester='" + courseSemester + '\'' +
                ", courseType=" + courseType +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseTeacherDescription='" + courseTeacherDescription + '\'' +
                ", courseCredit=" + courseCredit +
                ", teacherId=" + teacherId +
                '}';
    }
}
