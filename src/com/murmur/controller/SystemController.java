package com.murmur.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.murmur.kit.ResultCodeEnum;
import com.murmur.kit.ResultData;
import com.murmur.po.Course;
import com.murmur.po.File;
import com.murmur.po.Notice;
import com.murmur.po.Student;
import com.murmur.service.SystemService;

@Controller
@RequestMapping("/system")
public class SystemController {
	
	private ResultData resultData;
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping("/login")
	@ResponseBody
	public ResultData login(String name,String password,String userType) {
		if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)
				&& StringUtils.isNotBlank(userType)) {
			resultData = systemService.login(name, password, userType);
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
			
		return resultData;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public ResultData logout(Long id, String userType ) {
		if(id != null && StringUtils.isNotBlank(userType)) {
			resultData = systemService.logout(id, userType);
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}

		return resultData;
	}
	
	@RequestMapping("/modifyInfo")
	@ResponseBody
	public ResultData modifyInfo(Long id, String password, String userType) {
		if(id != null && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(userType)) {
			resultData = systemService.modifyInfo(id, password, userType);
			
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	@RequestMapping("/searchCourse")
	@ResponseBody
	public ResultData searchCourse(Long studentId, Long courseId, Course course) {
		System.out.println("studentId:"+studentId+",courseId:"+courseId+",course"+course);
		if(courseId != null) {
			course.setId(courseId);
		}
		resultData = systemService.searchCourse(studentId, course);
		return resultData;
	}
	
	
	@RequestMapping("/searchStudent")
	@ResponseBody
	public ResultData searchStudent(Long courseId, Long studentId, Student student) {
		System.out.println("courseId:"+courseId+", studentId:"+studentId+",student:"+student);
		if(studentId != null) {
			student.setId(studentId);
		}
		resultData = systemService.searchStudent(courseId, student);
		return resultData;
	}
	
	
	@RequestMapping("/searchNotice")
	@ResponseBody
	public ResultData searchNotice(Notice notice) {
		if(notice != null && notice.getCourseId() != null) {
			resultData = systemService.searchNotice(notice);
		}else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		System.out.println("-----------------------------------");
		System.out.println(resultData);
		System.out.println("-----------------------------------");
		return resultData;
	}
	
	
	@RequestMapping("/searchFile")
	@ResponseBody
	public ResultData searchFile(File file) {
		if(file != null && file.getCourseId() != null) {
			resultData = systemService.searchFile(file);
		}else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		System.out.println(resultData);
		return resultData;
	}
	
	
	@RequestMapping("/downloadFile")
	@ResponseBody
	public ResultData downloadFile(Long fileId) {
		if(fileId != null) {
			File file = new File();
			file.setId(fileId);
			resultData = systemService.searchFile(file);
		}else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		System.out.println(resultData);
		return resultData;
	}

}
