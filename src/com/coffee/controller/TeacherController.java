package com.coffee.controller;

import com.coffee.constant.FileStorage;
import com.coffee.po.Attachment;
import com.coffee.po.File;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coffee.kit.ResultCodeEnum;
import com.coffee.kit.ResultData;
        import com.coffee.po.Notice;
import com.coffee.service.TeacherService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Handler;

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
				try{
					resultData = teacherService.addNotice(notice);
				}catch(Exception e){
					e.printStackTrace();
					resultData = new ResultData();
					resultData.setResult(ResultCodeEnum.SERVER_ERROR);
				}
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
			try{
				resultData = teacherService.deleteNotice(noticeId);
			}catch(Exception e){
				e.printStackTrace();
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.SERVER_ERROR);
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	@RequestMapping("/modifyNotice")
	@ResponseBody
	public ResultData modifyNotice(Notice notice) {
		if(notice != null && notice.getId() != null) {
			try{
				resultData = teacherService.modifyNotice(notice);
			}catch(Exception e){
				e.printStackTrace();
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.SERVER_ERROR);
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	@RequestMapping("/addFile")
	@ResponseBody
	public ResultData addFile(Long courseId, Long uploaderId, String fileName , String fileDescription,
							  @RequestParam("file") List<MultipartFile> files,HttpServletRequest req) {
		System.out.println("courseId:"+courseId+",uploaderId:"+uploaderId+",fileName:"+fileName);
		if(courseId != null && uploaderId != null && StringUtils.isNotBlank(fileName)){
			if(files != null && files.size() > 0){
				File file = new File();
				file.setCourseId(courseId);
				file.setUploaderId(uploaderId);
				file.setFileName(fileName);
				if(fileDescription != null)
					file.setFileDescription(fileDescription);
				String dirPath = req.getServletContext().getRealPath(FileStorage.FILE_STORAGE_PATH);
				try{
					resultData = teacherService.addFile(file,dirPath,files);
				}catch(Exception e){
					e.printStackTrace();
					resultData = new ResultData();
					resultData.setResult(ResultCodeEnum.SERVER_ERROR);
				}
			} else{
				resultData.setResult(ResultCodeEnum.FILE_UPLOAD_EMPTY);  //上传附件为空
			}
		}else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);  //重要参数为空
		}

		return resultData;
	}

	@RequestMapping("/addAttachment")
	@ResponseBody
	public ResultData addAttachment(Long fileId, @RequestParam("file") List<MultipartFile> files, HttpServletRequest req) {
		if(fileId != null){
			if(files != null && files.size() > 0){
				Attachment attachment = new Attachment();
				attachment.setFileId(fileId);
				attachment.setAttachmentPath(req.getServletContext().getRealPath(FileStorage.FILE_STORAGE_PATH));
				try{
					resultData = teacherService.addAttachment(attachment,files);
				}catch(Exception e){
					e.printStackTrace();
					resultData = new ResultData();
					resultData.setResult(ResultCodeEnum.SERVER_ERROR);
				}
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
			try{
				resultData = teacherService.deleteFile(fileId);
			}catch(Exception e){
				e.printStackTrace();
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.SERVER_ERROR);
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}

	@RequestMapping("/deleteAttachment")
	@ResponseBody
	public ResultData deleteAttachment(Long attachmentId) {
		if(attachmentId != null) {
			Attachment attachment = new Attachment();
			attachment.setId(attachmentId);
			try{
				resultData = teacherService.deleteAttachment(attachment);
			}catch(Exception e){
				e.printStackTrace();
				resultData = new ResultData();
				resultData.setResult(ResultCodeEnum.SERVER_ERROR);
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);
		}
		return resultData;
	}
	
	

}
