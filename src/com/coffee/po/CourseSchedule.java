package com.coffee.po;

import java.lang.reflect.Field;

public class CourseSchedule {
    private long id;
    private Long studentId;
    private Long courseId;

    public CourseSchedule() {
    }

    public CourseSchedule(Long id) {
        this.id = id;
    }

    public CourseSchedule(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CourseSchedule{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
