package com.coffee.service;


import com.coffee.po.PageParam;
import com.coffee.kit.ResultData;
import com.coffee.po.Course;
import com.coffee.po.Student;
import com.coffee.po.Teacher;

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
	public ResultData searchTeacher(Teacher teacher, PageParam pageParam);
	
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

}
