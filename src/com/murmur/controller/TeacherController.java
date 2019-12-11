package com.murmur.controller;

import com.murmur.po.File;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	private ResultData resultData;
	
	@Autowired
	private TeacherService teacherService;

	public TeacherController() {
		resultData = new ResultData();
	}

	@RequestMapping("/addNotice")
	@ResponseBody
	public ResultData addNotice(Notice notice) {
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
	public ResultData addFile(Long courseId, Long uploaderId, String fileName , String fileDescription,
							  @RequestParam("file") List<MultipartFile> files,
							  HttpServletRequest req) {
		System.out.println("courseId:"+courseId+",uploaderId:"+uploaderId+",fileName:"+fileName);
		if(courseId != null && uploaderId != null && StringUtils.isNotBlank(fileName)){
			if(files.isEmpty() || files.size() > 0){
				File file = new File();
				file.setCourseId(courseId);
				file.setUploaderId(uploaderId);
				file.setFileName(fileName);
				if(fileDescription != null)
					file.setFileDescription(fileDescription);
				String dirPath = req.getServletContext().getRealPath("/upload/");
				System.out.println("getServletContext"+req.getServletContext());
				resultData = teacherService.addFile(file,dirPath,files);
			} else{
				resultData.setResult(ResultCodeEnum.FILE_UPLOAD_EMPTY);  //上传附件为空
			}
		}else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);  //重要参数为空
		}

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
