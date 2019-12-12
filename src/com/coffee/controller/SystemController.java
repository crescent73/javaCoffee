package com.coffee.controller;

import com.coffee.constant.FileStorage;
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

import javax.servlet.http.HttpServletRequest;
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
	public ResponseEntity downloadAttachment(Long attachmentId, HttpServletRequest req) {
		if (attachmentId != null) {
			String dirPath = req.getServletContext().getRealPath("\\");
			AttachmentDetail attachmentDetail = systemService.downloadAttachment(dirPath, attachmentId);
			System.out.println(attachmentDetail);
			if(attachmentDetail != null) {
				InputStreamResource resource;
				try {
					resource = new InputStreamResource(new FileInputStream(attachmentDetail.getFile()));
				} catch (IOException e) {
					e.printStackTrace();
					resultData = new ResultData();
					resultData.setResult(ResultCodeEnum.FILE_DOWNLOAD_FAILURE); //文件下载失败
					return ResponseEntity.ok().body(resultData);
				}
				HttpHeaders headers = new HttpHeaders();
				headers.setContentDispositionFormData("attachment", attachmentDetail.getAttachmentName());
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				return ResponseEntity.ok().headers(headers).body(resource);
			} else {
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.FILE_DOWNLOAD_FAILURE); //文件下载失败
				return ResponseEntity.ok().body(resultData);
			}
		} else {
			resultData = new ResultData();
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //返回重要参数为空
			return ResponseEntity.ok().body(resultData);
		}
	}


}
