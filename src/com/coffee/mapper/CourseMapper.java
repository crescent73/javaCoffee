package com.coffee.mapper;

import com.coffee.po.*;

import java.util.List;

public interface CourseMapper {
    public List<CourseDetail> find(Course course);
    public int update(Course course);
    public int insert(Course course);
    public int delete(Course course);
    public Teacher findTeacherByCourseId(Long courseId);
    public List<Student> findStudentByCourseId(Long courseId);
    public List<File> findFileByCourseId(Long courseId);
    public List<Notice> findNoticeByCourseId(Long courseId);

    /**
     * 搜索字段 课程名 课程号
     * @param key 搜索词
     * @return 结果
     */
    public List<Course> search(String key);
}
