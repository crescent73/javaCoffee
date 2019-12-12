package com.coffee.po;

public class CourseDetail extends Course {
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                ", courseName='" + getCourseName() + '\'' +
                ", courseNumber='" + getCourseNumber() + '\'' +
                ", courseSchool=" + getCourseSchool() +
                ", courseSemester='" + getCourseSemester() + '\'' +
                ", courseType=" + getCourseType() +
                ", courseDescription='" + getCourseDescription() + '\'' +
                ", courseTeacherDescription='" + getCourseTeacherDescription() + '\'' +
                ", courseCredit=" + getCourseCredit() +
                ", teacherId=" + getTeacherId() +
                ", teacherName=" + teacherName +
                '}';
    }
}
