package com.murmur.controller;

//import org.apache.bval.util.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.murmur.kit.ResultCodeEnum;
import com.murmur.kit.ResultData;
        import com.murmur.po.Notice;
import com.murmur.service.TeacherService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	private ResultData resultData;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("/addNotice")
	@ResponseBody
	public ResultData addNotice(Notice notice) {
		resultData = new ResultData();
		if(notice != null) {
			if(notice.getCourseId() != null && notice.getPublisherId() != null
					&& StringUtils.isNotBlank(notice.getNoticeTitle())
					&& StringUtils.isNotBlank(notice.getNoticeContent())
					&& notice.getNoticeLevel()!= null) {
				resultData = teacherService.addNotice(notice);
			} else {
				
				resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
			}
				
		}else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	@RequestMapping("/deleteNotice")
	@ResponseBody
	public ResultData deleteNotice(Long noticeId) {
		if(noticeId != null) {
			resultData = teacherService.deleteNotice(noticeId);
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	@RequestMapping("/modifyNotice")
	@ResponseBody
	public ResultData modifyNotice(Notice notice) {
		if(notice != null && notice.getId() != null) {
			resultData = teacherService.modifyNotice(notice);
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		
		return resultData;
	}
	
	@RequestMapping("/addFile")
	@ResponseBody
	public ResultData addFile(Long courseId, Long uploaderId, String fileName ,@RequestParam("file") List<MultipartFile> files) {
		System.out.println("courseId:"+courseId+",uploaderId:"+uploaderId+",fileName:"+fileName);
		if(files!=null){
			for(MultipartFile file:files)
				System.out.println("files:"+file.getName()+file.getOriginalFilename());
		}
//		resultData = teacherService.addFile(new File());
		return resultData;
	}
	
	@RequestMapping("/deleteFile")
	@ResponseBody
	public ResultData deleteFile(Long fileId) {
		if(fileId != null) {
			resultData = teacherService.deleteFile(fileId);
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	

}
