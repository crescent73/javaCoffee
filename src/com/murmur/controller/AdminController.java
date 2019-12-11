package com.murmur.controller;

import com.murmur.kit.PageParam;
import com.murmur.kit.ResultCodeEnum;
import com.murmur.kit.ResultData;
import com.murmur.po.Course;
import com.murmur.po.Student;
import com.murmur.po.Teacher;
import com.murmur.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	private ResultData resultData;
	
	@RequestMapping("/addCourse")
	@ResponseBody
	public ResultData addCourse(Course course) {
		System.out.println(course);
		if(StringUtils.isNotBlank(course.getCourseName()) && StringUtils.isNotBlank(course.getCourseNumber()) 
				&& StringUtils.isNotBlank(course.getCourseSemester()) && course.getTeacherId() != null
				&& course.getCourseType() != null && course.getCourseCredit() != null
				&& course.getCourseSchool() != null) {
			//调用CourseService
			resultData = adminService.addCourse(course);
			
			
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);//必要请求参数为空
		}
		// courseType courseCredit  courseSchool
		System.out.println(resultData);
		return resultData;
	}
	
	@RequestMapping(value="/deleteCourse")
	@ResponseBody
	public ResultData deleteCourse(Long courseId) {
		
//		String courseId = req.getParameter("courseId");
		System.out.println(courseId);
		if(courseId!=null) {
			//调用删除课程service
			resultData = adminService.deleteCourse(courseId);
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		
		
		return resultData;
	}
	
	@RequestMapping("/modifyCourse")
	@ResponseBody
	public ResultData modifyCourse(Course course) {
		resultData = new ResultData();
		
		if(course.getId() != null) {
			//调用修改课程service
			if(!(StringUtils.isBlank(course.getCourseSemester()) && course.getTeacherId() == null
					&& course.getCourseType() == null && course.getCourseCredit() == null
					&& course.getCourseSchool() == null)) {
				// 修改数据不都为空
				System.out.println(course);
				resultData = adminService.modifyCourse(course);
			} else {
				resultData.setResult(ResultCodeEnum.DB_UPDATE_REQUEST_NULL); //至少有一个修改数据
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		
		return resultData;
	}
	
	@RequestMapping("/addTeacher")
	@ResponseBody
	public ResultData addTeacher(Teacher teacher) {
		resultData = new ResultData();
		System.out.println(teacher);
		if(StringUtils.isNotBlank(teacher.getTeacherName()) && StringUtils.isNotBlank(teacher.getTeacherNumber())
				&& StringUtils.isNotBlank(teacher.getTeacherEmail()) 
				&& StringUtils.isNoneBlank(teacher.getTeacherTitle())
				&& StringUtils.isNotBlank(teacher.getTeacherPassword())
				&& teacher.getTeacherSchool() != null) {
			resultData = adminService.addTeacher(teacher);  //添加老师
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		
		
		return resultData;
	}
	
	@RequestMapping("/deleteTeacher")
	@ResponseBody
	public ResultData deleteTeacher(Long teacherId) {
		System.out.println(teacherId);
		if(teacherId != null) {
			resultData = adminService.deleteTeacher(teacherId);
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		
		return resultData;
	}
	
	@RequestMapping("/searchTeacher")
	@ResponseBody
	public ResultData searchTeacher(Teacher teacher, PageParam pageParam) {
		System.out.println(teacher);
		if(teacher != null) {
			resultData = adminService.searchTeacher(teacher,pageParam);
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		
		return resultData;
	}
	
	@RequestMapping("/addStudent")
	@ResponseBody
	public ResultData addStudent(Student student) {
		if(student != null && StringUtils.isNotBlank(student.getStudentName())
				&& StringUtils.isNotBlank(student.getStudentNumber())
				&& StringUtils.isNotBlank(student.getStudentClass())
				&& StringUtils.isNotBlank(student.getStudentGrade())
				&& StringUtils.isNotBlank(student.getStudentEmail())
				&& StringUtils.isNotBlank(student.getStudentPassword())
				&& student.getStudentSchool() != null) {
			resultData = adminService.addStudent(student);
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	@RequestMapping("/deleteStudent")
	@ResponseBody
	public ResultData deleteStudent(Long studentId) {
		if(studentId != null) {
			resultData = adminService.deleteStudent(studentId);
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	
	

}
