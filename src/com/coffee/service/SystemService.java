package com.coffee.service;


import com.coffee.po.PageParam;
import com.coffee.kit.ResultData;
import com.coffee.po.*;

import javax.servlet.http.HttpSession;

public interface SystemService {
	
	/**
	 * 系统登陆入口：
	 * 为管理员，教师，学生提供统一登陆接口
	 * @param user 用户
	 * @return
	 */
	public ResultData login(User user);
	
	/**
	 * 退出系统
	 * @param id 用户id
	 * @param userType 用户类型：1：管理员；2：教师；3：学生 
	 * @return
	 */
	public ResultData logout(Long id, String userType);
	
	/**
	 * 修改用户信息
	 * @param id 用户id
	 * @param password 用户密码
	 * @param userType 用户类型：1：管理员；2：教师；3：学生 
	 * @return
	 */
	public ResultData modifyInfo(Long id, String password, String userType, HttpSession httpSession);
	
	/**
	 * 查询课程列表
	 * 为管理员、教师和学生都提供查询课程列表的功能
	 * @param studentId 学生id, 若存在，则会查询该学生当前学期所有课程
	 * @param course 课程信息
	 * @return
	 */
	public ResultData searchCourse(Long studentId, Course course, String searchKey, PageParam pageParam);
	
	/**
	 * 查询学生列表
	 * 为管理员和教师提供查询学生功能
	 * @param courseId 课程id，若课程id存在，则会查询该课程下的学生列表
	 * @param student 学生信息
	 * @return
	 */
	public ResultData searchStudent(Long courseId, Student student, String searchKey, PageParam pageParam);
	
	/**
	 * 查询公告列表
	 * 为教师和学生提供查询公告功能
	 * @param notice 公告信息，课程id为必须值
	 * @return
	 */
	public ResultData searchNotice(Notice notice, PageParam pageParam);
	
	/**
	 * 查询文件&下载文件
	 * 为教师和学生提供查询文件地址功能
	 * 
	 * @param file file信息
	 * @return file地址
	 */
	public ResultData searchFile(File file, PageParam pageParam);

	/**
	 * 下载附件
	 * @param attachment 附件i
	 * @return 附件文件
	 */
	public AttachmentDetail downloadAttachment(Attachment attachment);

	/**
	 * 查询附件
	 * @param attachment 附件信息
	 * @return 附件信息
	 */
	public  ResultData searchAttachment(Attachment attachment, PageParam pageParam);


	public ResultData searchStudentByKey(String key);

	public ResultData searchCourseByKey(String key);

}
