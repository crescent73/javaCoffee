package com.coffee.service;


import com.coffee.po.*;
import com.coffee.kit.ResultData;

import java.util.List;

public interface AdminService {
	/**
	 * 添加课程
	 * @param course 课程对象
	 * @return 封装的返回数据
	 */
	public ResultData addCourse(Course course);
	
	/**
	 * 删除课程
	 * @param id 课程id
	 * @return
	 */
	public ResultData deleteCourse(Long id);
	
	/**
	 * 修改课程
	 * @param course 课程对象
	 * @return
	 */
	public ResultData modifyCourse(Course course);
	
	/**
	 * 添加教师
	 * @param teacher
	 * @return
	 */
	public ResultData addTeacher(Teacher teacher);
	
	/**
	 * 删除教师
	 * @param id 教师id
	 * @return
	 */
	public ResultData deleteTeacher(Long id);
	
	/**
	 * 查询教师
	 * @param teacher
	 * @return
	 */
	public ResultData searchTeacher(Teacher teacher,String searchKey, PageParam pageParam);
	
	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	public ResultData addStudent(Student student);
	
	/**
	 * 删除学生
	 * @param id 学生id
	 * @return
	 */
	public ResultData deleteStudent(Long id);

	public ResultData searchTeacherByKey(String searchKey);

	/**
	 * 添加选课表
	 * 支持一个学生添加多个课程
	 * 一个课程添加多个学生
	 * @param studentId 学生id列表
	 * @param courseId 课程id列表
	 * @return
	 */
	public ResultData addCourseSchedule(List<Long> studentId, List<Long> courseId);

	/**
	 * 删除选课
	 * @param id 选课id
	 * @return
	 */
	public ResultData deleteCourseSchedule(Long id);

}
