package com.coffee.controller;

import com.coffee.po.PageParam;
import com.coffee.po.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coffee.kit.ResultCodeEnum;
import com.coffee.kit.ResultData;
import com.coffee.service.SystemService;

import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/system")
public class SystemController {
	
	private ResultData resultData;
	
	@Autowired
	private SystemService systemService;


	@RequestMapping("/login")
	@ResponseBody
	public ResultData login(String name, String password, String userType, HttpSession session) {
		if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)
				&& StringUtils.isNotBlank(userType)) {
			resultData = systemService.login(name, password, userType);
			if(resultData.getCode().equals("400")){
				System.out.println("login success");
				//登陆成功设置用户名
				session.setAttribute("login",1); //设置登陆状态是1
				session.setAttribute("username",name);  //记录用户名
			} else{
				System.out.println(resultData.getCode());
			}
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public ResultData logout(Long id, String userType,HttpSession session) {
		if(id != null && StringUtils.isNotBlank(userType)) {
			if (session.getAttribute("login") == null){
				resultData.setResult(ResultCodeEnum.NO_LOGIN_USER);
			}else if(session.getAttribute("login").equals(1)){
				//退出登陆成功
				session.removeAttribute("login");
				resultData.setResult(ResultCodeEnum.LOGOUT_SUCCESS);
			} else {
				resultData.setResult(ResultCodeEnum.UNKOWN_ERROE);
			}
//			resultData = systemService.logout(id, userType);
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
	public ResultData searchCourse(Long studentId, Long courseId, Course course, PageParam pageParam) {
		System.out.println("studentId:"+studentId+",courseId:"+courseId+",course"+course);
		if(courseId != null) {
			course.setId(courseId);
		}
		resultData = systemService.searchCourse(studentId, course, pageParam);
		return resultData;
	}
	
	
	@RequestMapping("/searchStudent")
	@ResponseBody
	public ResultData searchStudent(Long courseId, Long studentId, Student student, PageParam pageParam) {
		System.out.println("courseId:"+courseId+", studentId:"+studentId+",student:"+student);
		if(studentId != null) {
			student.setId(studentId);
		}
		resultData = systemService.searchStudent(courseId, student, pageParam);
		return resultData;
	}
	
	
	@RequestMapping("/searchNotice")
	@ResponseBody
	public ResultData searchNotice(Notice notice, PageParam pageParam) {
		if(notice != null && notice.getCourseId() != null) {
			System.out.println(pageParam);
			resultData = systemService.searchNotice(notice,pageParam);
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
	public ResultData searchFile(File file, PageParam pageParam) {
		if(file != null && file.getCourseId() != null) {
			resultData = systemService.searchFile(file,pageParam);
		}else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		System.out.println(resultData);
		return resultData;
	}
	
	
	@RequestMapping("/downloadAttachment")
	@ResponseBody
	public ResponseEntity downloadAttachment(Long attachmentId) {
		if (attachmentId != null) {
			Attachment attachment = new Attachment();
			attachment.setId(attachmentId);
			AttachmentDetail attachmentDetail = systemService.downloadAttachment(attachment);
			System.out.println(attachmentDetail);
			if(attachmentDetail != null) {
				InputStreamResource resource;
				try {
					resource = new InputStreamResource(new FileInputStream(attachmentDetail.getFile()));
				} catch (IOException e) {
					e.printStackTrace();
					resultData = new ResultData();
					resultData.setResult(ResultCodeEnum.ATTACHMENT_DOWNLOAD_FAILURE); //文件下载失败
					return ResponseEntity.ok().body(resultData);
				}
				HttpHeaders headers = new HttpHeaders();
				headers.setContentDispositionFormData("attachment", attachmentDetail.getAttachmentName());
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				return ResponseEntity.ok().headers(headers).body(resource);
			} else {
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.ATTACHMENT_DOWNLOAD_FAILURE); //文件下载失败
				return ResponseEntity.ok().body(resultData);
			}
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //返回重要参数为空
			return ResponseEntity.ok().body(resultData);
		}
	}

	@RequestMapping("/searchStudentByKey")
	@ResponseBody
	public ResultData searchStudentByKey(String key) {
		if(StringUtils.isNotBlank(key)) {
			resultData = systemService.searchStudentByKey(key);
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}

	@RequestMapping("/searchCourseByKey")
	@ResponseBody
	public ResultData searchCourseByKey(String key) {
		if(StringUtils.isNotBlank(key)) {
			resultData = systemService.searchCourseByKey(key);
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}

}
