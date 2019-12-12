package com.coffee.service.impl;


import java.util.List;
import java.util.UUID;

import com.coffee.constant.FileStorage;
import com.coffee.mapper.*;
import com.coffee.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coffee.kit.ResultCodeEnum;
import com.coffee.kit.ResultData;
import com.coffee.service.TeacherService;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
	
	private ResultData resultData;
	
	@Autowired
	private NoticeMapper noticeDao;
	
	@Autowired
	private FileMapper fileDao;
	
	@Autowired
	private CourseMapper courseDao;
	
	@Autowired
	private TeacherMapper teacherDao;

	@Autowired
	private AttachmentMapper attachmentDao;

	public TeacherServiceImpl() {
		resultData = new ResultData();
	}

	@Override
	public ResultData addNotice(Notice notice) {
		if(notice != null) {
			//course外键校验
			if(!isCourseExist(notice.getCourseId())) {
				resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_COURSE_NOT_EXIST);//课程不存在
				return resultData;
			}
			//teacher外键校验
			if(!isTeacherExist(notice.getPublisherId())) {
				resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_TEACHER_NOT_EXIST);//教师不存在
				return resultData;
			}
			
			int result = noticeDao.insert(notice);
			if(result > 0) {
				resultData.setResult(ResultCodeEnum.DB_ADD_SUCCESS); //添加成功
			} else {
				resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE); //添加失败
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);  //必要参数为空
		}
		return resultData;
	}

	@Override
	public ResultData deleteNotice(Long id) {
		if(id != null) {
			Notice notice = new Notice();
			notice.setId(id);
			int result = noticeDao.delete(notice);
			if(result > 0) {
				resultData.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
			} else {
				resultData.setResult(ResultCodeEnum.DB_DELETE_FAILURE);
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);  //必要参数为空
		}
		return resultData;
	}

	@Override
	public ResultData modifyNotice(Notice notice) {
		if(notice != null && notice.getId() > 0) { //course不为空且id存在
			//公告校验
			if(!isNoticeExist(notice.getId())) {
				resultData.setResult(ResultCodeEnum.DB_UPDATE_FAILURE_NOTICE_NOT_EXIST);//公告不存在
				return resultData;
			}
			//course外键校验
			if(!isCourseExist(notice.getCourseId())) {
				resultData.setResult(ResultCodeEnum.DB_UPDATE_FAILURE_COURSE_NOT_EXIST);//课程不存在
				return resultData;
			}
			//teacher外键校验
			if(!isTeacherExist(notice.getPublisherId())) {
				resultData.setResult(ResultCodeEnum.DB_UPDATE_FAILURE_TEACHER_NOT_EXIST);//教师不存在
				return resultData;
			}
			
            int result = noticeDao.update(notice);
            if(result > 0) {
                resultData.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS); //修改成功
            } else {
                resultData.setResult(ResultCodeEnum.DB_UPDATE_ERROR);  //修改失败
            }
        } else {
            resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //id直接返回数据必要参数为空
        }
		return resultData;
	}

	@Override
	public ResultData addFile(File file,String dirPath,List<MultipartFile> attachments) {
		boolean isSuccess = false;
		// 处理file
		if(file != null) {
			//course外键校验
			if (!isCourseExist(file.getCourseId())) {
				resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_COURSE_NOT_EXIST);//课程不存在
				return resultData;
			}
			//teacher外键校验
			if (!isTeacherExist(file.getUploaderId())) {
				resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_TEACHER_NOT_EXIST);//教师不存在
				return resultData;
			}
			int result = fileDao.insert(file);
			if(result > 0) {
				isSuccess = true;
//				System.out.println("fileID:"+file.getId());
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);  //必要参数为空
			return resultData;
		}

		// 处理attachment
		if(attachments !=null && !attachments.isEmpty() && attachments.size() > 0){
			for(MultipartFile attachment : attachments){
				Attachment myAttachment = new Attachment();
				String originalFileName = attachment.getOriginalFilename();
//				System.out.println("atttachment:"+originalFileName);
				System.out.println("dirPath: " + dirPath);
				java.io.File filePath = new java.io.File(dirPath);
				if(!filePath.exists()) {
					filePath.mkdirs();
				}
				//使用UUID给附件重新命名（附件名+文件id+uuid+）
				String newFileName = file.getId() + "_" + UUID.randomUUID()+ "_" + originalFileName;
				System.out.println("newFileName: "+newFileName);
				//将附件添加到文件夹中
				try {
					attachment.transferTo(new java.io.File(dirPath +"\\"+ newFileName));
				} catch (Exception e){
					e.printStackTrace();
					resultData.setCode("407");
					resultData.setMsg("附件："+originalFileName + "，上传失败");
					return resultData;
				}
				//将附件添加到数据库中
				myAttachment.setFileId(file.getId());
				myAttachment.setAttachmentName(originalFileName);  //name存储的是原文件名
				myAttachment.setAttachmentPath(FileStorage.FILE_STORAGE_PATH +"\\"+newFileName); //path存储的是路径和新文件名
				System.out.println("myAttachmentPath:"+myAttachment.getAttachmentPath());
				int result = attachmentDao.insert(myAttachment);
				isSuccess = result > 0;
			}
		} else{
			resultData.setResult(ResultCodeEnum.FILE_UPLOAD_EMPTY);//上传附件为空
			return resultData;
		}
		if(isSuccess){
			resultData.setResult(ResultCodeEnum.FILE_UPLOAD_SUCCESS);//文件上传成功
		} else {
			resultData.setResult(ResultCodeEnum.FILE_UPLOAD_FAILURE);//文件上传失败
		}
		return resultData;
	}

	@Override
	public ResultData deleteFile(Long id) {
		if(id != null) {
			File file = new File();
			file.setId(id);
			int result = fileDao.delete(file);
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!删除系统文件!!!!!!!!!!!!!!!!!!!!!!
			if(result > 0) {
				resultData.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
			} else {
				resultData.setResult(ResultCodeEnum.DB_DELETE_FAILURE);
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);  //必要参数为空
		}
		return resultData;
	}
	
	private boolean isNoticeExist(Long noticeId) {
		List<NoticeDetail> notices = null;
		Notice notice = new Notice();
		if(noticeId != null) {
			notice.setId(noticeId);
			notices = noticeDao.find(notice);
		}
		if(notices != null && notices.size() >0)
			return true;
		else 
			return false;
	}
	
	/**
     * 判断老师是否存在
     * 外键约束条件，若老师不存在插入修改报错
     * @param teacherId
     * @return 存在 true 不存在 false
     */
    private boolean isTeacherExist(Long teacherId) {
    	List<Teacher> teachers = null;
        Teacher teacher = new Teacher();
        if(teacherId != null) {
        	teacher.setId(teacherId);
            teachers = teacherDao.find(teacher);
        }
        if(teacherId!= null && teachers.size() > 0) 
        	return true;
        else 
        	return false;
    }
    
    /**
     * 判断课程是否存在
     * 外键约束条件，若课程不存在插入修改报错
     * @param courseId
     * @return 存在 true 不存在 false
     */
    private boolean isCourseExist(Long courseId) {
    	List<CourseDetail> courses = null;
        Course course = new Course();
        if(courseId != null) {
        	course.setId(courseId);
            courses = courseDao.find(course);
        }
        if(courseId!= null && courses.size() == 1) 
        	return true;
        else 
        	return false;
    }
}
