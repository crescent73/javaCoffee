package com.murmur.controller;

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
import java.io.File;
import java.util.List;
import java.util.UUID;

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
	public ResultData addFile(Long courseId, Long uploaderId, String fileName ,
							  @RequestParam("file") List<MultipartFile> files,
							  HttpServletRequest req) {
		System.out.println("courseId:"+courseId+",uploaderId:"+uploaderId+",fileName:"+fileName);
		if(!files.isEmpty() && files.size() > 0){
			for(MultipartFile file:files){
				System.out.println("files:"+file.getName()+file.getOriginalFilename());
				String originalFileName = file.getOriginalFilename();
				String dirPath = req.getServletContext().getRealPath("/upload/");
				System.out.println("dirPath: " + dirPath);
				File filePath = new File(dirPath);
				if(!filePath.exists()) {
					filePath.mkdirs();
				}
				//使用UUID给文件重新命名（课程id+老师id+uuid+文件名）
				String newFileName = courseId + "_" + uploaderId + "_" + UUID.randomUUID() + "_" + originalFileName;
				System.out.println("newFileName: "+newFileName);
				try {
					file.transferTo(new File(dirPath + newFileName));
				} catch (Exception e){
					e.printStackTrace();
					resultData.setResult(ResultCodeEnum.FILE_UPLOAD_FAILURE);
					return resultData;
				}
			}
			resultData.setResult(ResultCodeEnum.FILE_UPLOAD_SUCCESS);
		} else{
			resultData.setResult(ResultCodeEnum.FILE_UPLOAD_FAILURE);
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
