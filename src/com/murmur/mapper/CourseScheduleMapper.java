package com.murmur.mapper;

import com.murmur.po.Course;
import com.murmur.po.CourseDetail;
import com.murmur.po.CourseSchedule;
import com.murmur.po.Student;

import java.util.List;

public interface CourseScheduleMapper {
    public List<CourseDetail> findCourseByStudentId(Long studentId);
    public List<Student> findStudentByCourseId(Long courseId);
    public int delete(CourseSchedule courseSchedule);
    public int insert(CourseSchedule courseSchedule);
}
