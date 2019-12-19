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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coffee.kit.ResultCodeEnum;
import com.coffee.kit.ResultData;
import com.coffee.service.SystemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController {
	
	private ResultData resultData;
	
	@Autowired
	private SystemService systemService;


	@RequestMapping("/login")
	@ResponseBody
	public ResultData login(User user, HttpSession session) {
		if(StringUtils.isNotBlank(user.getName()) && StringUtils.isNotBlank(user.getPassword())
				&& StringUtils.isNotBlank(user.getUserType())) {
			try{
				resultData = systemService.login(user);
			}catch(Exception e){
				e.printStackTrace();
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.SERVER_ERROR);
			}
			if(resultData.getCode().equals("400")){
				System.out.println("login success");
				//登陆成功设置用户名
				System.out.println("user:"+user);
				session.setAttribute("login",1); //设置登陆状态是1
				session.setAttribute("user",user);  //记录用户

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
			resultData = new ResultData();
			if (session.getAttribute("login") == null){
				resultData.setResult(ResultCodeEnum.NO_LOGIN_USER);
			}else if(session.getAttribute("login").equals(1)){
				//退出登陆成功
				session.removeAttribute("login");
				session.removeAttribute("user");  //记录用户
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
			try{
				resultData = systemService.modifyInfo(id, password, userType);
			}catch(Exception e){
				e.printStackTrace();
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.SERVER_ERROR);
			}
			
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	@RequestMapping("/searchCourse")
	@ResponseBody
	public ResultData searchCourse(Long studentId, Long courseId, Course course, String searchKey, PageParam pageParam) {
		System.out.println("studentId:"+studentId+",courseId:"+courseId+",course"+course);
		if(courseId != null) {
			course.setId(courseId);
		}
		try{
			resultData = systemService.searchCourse(studentId, course,searchKey, pageParam);
		}catch(Exception e){
			e.printStackTrace();
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.SERVER_ERROR);
		}
		return resultData;
	}
	
	
	@RequestMapping("/searchStudent")
	@ResponseBody
	public ResultData searchStudent(Long courseId, Long studentId, Student student, String searchKey,PageParam pageParam) {
		System.out.println("courseId:"+courseId+", studentId:"+studentId+",student:"+student);
		if(studentId != null) {
			student.setId(studentId);
		}
		try{
			resultData = systemService.searchStudent(courseId, student,searchKey, pageParam);
		}catch(Exception e){
			e.printStackTrace();
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.SERVER_ERROR);
		}
		return resultData;
	}
	
	
	@RequestMapping("/searchNotice")
	@ResponseBody
	public ResultData searchNotice(Notice notice, PageParam pageParam) {
		if(notice != null && notice.getCourseId() != null) {
			System.out.println(pageParam);
			System.out.println(notice);
			try{
				resultData = systemService.searchNotice(notice,pageParam);
			}catch(Exception e){
				e.printStackTrace();
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.SERVER_ERROR);
			}
		}else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	
	@RequestMapping("/searchFile")
	@ResponseBody
	public ResultData searchFile(File file, PageParam pageParam) {
		if(file != null && file.getCourseId() != null) {
			try{
				resultData = systemService.searchFile(file,pageParam);
			}catch(Exception e){
				e.printStackTrace();
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.SERVER_ERROR);
			}
		}else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		System.out.println(resultData);
		return resultData;
	}
	
	
	@RequestMapping("/downloadAttachment")
	@ResponseBody
	public ResponseEntity downloadAttachment(Long attachmentId, @RequestBody Map<String,Object> map) {
		if(attachmentId == null){
			attachmentId = Integer.toUnsignedLong((Integer) map.get("attachmentId"));
		}
		System.out.println("attachmentId1"+attachmentId);
		System.out.println("attachmentId2"+map.get("attachmentId"));
		if (attachmentId != null) {
			Attachment attachment = new Attachment();
			attachment.setId(attachmentId);
			AttachmentDetail attachmentDetail = null;
			try{
				attachmentDetail = systemService.downloadAttachment(attachment);
			}catch(Exception e){
				e.printStackTrace();
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.SERVER_ERROR);
			}
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
			try{
				resultData = systemService.searchStudentByKey(key);
			}catch(Exception e){
				e.printStackTrace();
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.SERVER_ERROR);
			}
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
			try{
				resultData = systemService.searchCourseByKey(key);
			}catch(Exception e){
				e.printStackTrace();
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.SERVER_ERROR);
			}
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}

}
