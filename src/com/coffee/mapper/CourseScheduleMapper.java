package com.coffee.mapper;

import com.coffee.po.CourseDetail;
import com.coffee.po.CourseSchedule;
import com.coffee.po.Student;

import java.util.List;

public interface CourseScheduleMapper {
    public List<CourseDetail> findCourseByStudentId(Long studentId);
    public List<Student> findStudentByCourseId(Long courseId);
    public int delete(CourseSchedule courseSchedule);
    public int insert(CourseSchedule courseSchedule);
}
