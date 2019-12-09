package com.murmur.service.impl;


import java.util.List;

import com.murmur.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.murmur.kit.ResultCodeEnum;
import com.murmur.kit.ResultData;
import com.murmur.mapper.CourseMapper;
import com.murmur.mapper.FileMapper;
import com.murmur.mapper.NoticeMapper;
import com.murmur.mapper.TeacherMapper;
import com.murmur.service.TeacherService;

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


	@Override
	public ResultData addNotice(Notice notice) {
		resultData = new ResultData();
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
		resultData = new ResultData();
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
		resultData = new ResultData();
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
	public ResultData addFile(File file) {
		resultData = new ResultData();
		if(file != null) {
			//course外键校验
			if(!isCourseExist(file.getCourseId())) {
				resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_COURSE_NOT_EXIST);//课程不存在
				return resultData;
			}
			//teacher外键校验
			if(!isTeacherExist(file.getUploaderId())) {
				resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_TEACHER_NOT_EXIST);//教师不存在
				return resultData;
			}
			
			int result = fileDao.insert(file);
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
	public ResultData deleteFile(Long id) {
		resultData = new ResultData();
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
		List<Notice> notices = null;
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
