package com.coffee.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.coffee.kit.Data;
import com.coffee.po.PageParam;
import com.coffee.kit.ResultCodeEnum;
import com.coffee.kit.ResultData;
import com.coffee.mapper.*;
import com.coffee.po.*;
import com.coffee.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SystemServiceImpl implements SystemService {
	
	private ResultData<Data> resultData;

	@Autowired
	private AdminMapper adminDao;

	@Autowired
	private TeacherMapper teacherDao;

	@Autowired
	private StudentMapper studentDao;

	@Autowired
	private CourseMapper courseDao;

	@Autowired
	private NoticeMapper noticeDao;

	@Autowired
	private FileMapper fileDao;

	@Autowired
	private CourseScheduleMapper courseScheduleDao;

	@Autowired
	private AttachmentMapper attachmentDao;

	public SystemServiceImpl() {
		resultData = new ResultData<Data>();
	}

	@Override
	public ResultData login(String name, String password, String userType) {
		if(StringUtils.isNotBlank(name)&&StringUtils.isNotBlank(password)&&StringUtils.isNotBlank(userType)) {
			List result = null;
			switch(userType) {
			case "1":
				//调用adminDao
				Admin admin = new Admin();
				admin.setAdminName(name);
				admin.setAdminPassword(password);
				System.out.println(admin);
				result = adminDao.find(admin);
//				System.out.println(result);
				break;
			case "2":
				//调用teacherDao
				Teacher teacher = new Teacher();
				teacher.setTeacherName(name);
				teacher.setTeacherPassword(password);
				result = teacherDao.find(teacher);
				break;
			case "3":
				//调用studentDao
				Student student = new Student();
				student.setStudentName(name);
				student.setStudentPassword(password);
				result = studentDao.find(student);
				break;
			default:
				//返回错误信息
				resultData.setResult(ResultCodeEnum.PARA_FORMAT_ERROR); //参数格式错误
				return resultData;
			}
			//处理查询结果
			if(result.size() == 1) {
				Data data;
				switch(userType) {
					case "1": data = new Data<Admin>(); break;
					case "2": data = new Data<Teacher>(); break;
					case "3": data = new Data<Student>(); break;
					default:
						throw new IllegalStateException("Unexpected value: " + userType);
				}
				data.setData(result);
				resultData.setData(data);
				resultData.setResult(ResultCodeEnum.LOGIN_SUCCESS);  //登陆成功
			} else {
				resultData.setResult(ResultCodeEnum.LOGIN_ERROR); //登陆失败
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //必要请求参数为空
		}
		return resultData;
	}

	@Override
	public ResultData logout(Long id, String userType) {
		switch(userType) {
		case "1":
			//调用adminDao
			break;
		case "2":
			//调用teacherDao
			break;
		case "3":
			//调用studentDao
			break;
		default:
			//返回错误信息
			resultData.setResult(ResultCodeEnum.PARA_FORMAT_ERROR); //参数格式错误
			return resultData;
		}
		resultData.setResult(ResultCodeEnum.LOGOUT_SUCCESS);
		return resultData;
	}

	@Override
	public ResultData modifyInfo(Long id, String password, String userType) {
		if(id!=null &&StringUtils.isNotBlank(password)&&StringUtils.isNotBlank(userType)) {
			int result;
			switch(userType) {
			case "1":
				//调用adminDao
				Admin admin = new Admin();
				admin.setId(id);
				admin.setAdminPassword(password);
				
				result = adminDao.update(admin);
				break;
			case "2":
				//调用teacherDao
				Teacher teacher = new Teacher();
				teacher.setId(id);
				teacher.setTeacherPassword(password);
				System.out.println(teacher);
				result = teacherDao.update(teacher);
				break;
			case "3":
				//调用studentDao
				Student student = new Student();
				student.setId(id);
				student.setStudentPassword(password);
				result = studentDao.update(student);
				break;
			default:
				//返回错误信息
				resultData.setResult(ResultCodeEnum.PARA_FORMAT_ERROR); //参数格式错误
				return resultData;
			}
			if(result > 0) {
				resultData.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS); //更新成功
			} else {
				resultData.setResult(ResultCodeEnum.DB_UPDATE_ERROR); // 更新失败
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //必要请求参数为空
		}
		
		return resultData;
	}

	@Override
	public ResultData searchCourse(Long studentId, Course course, PageParam pageParam) {
		if(course == null) {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //必要请求参数为空
			return resultData;
		}
		System.out.println("studentId:"+studentId+",course:"+course);
		List<CourseDetail> courses;
		if(studentId != null) {//查询学生的课程列表
			if(pageParam != null && pageParam.isPaginate()){//是否分页
				PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
			}
			courses = courseScheduleDao.findCourseByStudentId((long)(studentId));
		} else {
			if(pageParam.isPaginate()){//是否分页
				PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
			}
			courses = courseDao.find(course);
		}
		System.out.println(courses);
		if(courses.size() > 0) {
			Data<List<CourseDetail>> data = new Data<List<CourseDetail>>();
			if(pageParam != null && pageParam.isPaginate()){
				PageInfo<CourseDetail> pageInfo = new PageInfo<>(courses);
				data.setData(pageInfo);
				data.setData(pageInfo.getList());
			} else {
				data.setData(courses);
			}
			resultData.setData(data);
			resultData.setResult(ResultCodeEnum.DB_FIND_SUCCESS); //查找成功
		} else {
			resultData.setResult(ResultCodeEnum.DB_FIND_FAILURE); //查找失败
		}
		return resultData;
	}

	@Override
	public ResultData searchStudent(Long courseId, Student student, PageParam pageParam) {
		if(student == null) {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //必要请求参数为空
			return resultData;
		}
		List<Student> students;
		if(courseId != null) {//查询某课程下的学生列表
			if(pageParam != null && pageParam.isPaginate()){//是否分页
				PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
			}
			students = courseScheduleDao.findStudentByCourseId(courseId);
		} else {
			if(pageParam != null && pageParam.isPaginate()){//是否分页
				PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
			}
			students = studentDao.find(student);
		}
		System.out.println(students);
		if(students.size() > 0) {
			Data<List<Student>> data = new Data<List<Student>>();
			if(pageParam != null && pageParam.isPaginate()){
				PageInfo<Student> pageInfo = new PageInfo<>(students);
				data.setData(pageInfo);
				data.setData(pageInfo.getList());
			} else {
				data.setData(students);
			}
			resultData.setData(data);
			resultData.setResult(ResultCodeEnum.DB_FIND_SUCCESS); //查找成功
		} else {
			resultData.setResult(ResultCodeEnum.DB_FIND_FAILURE); //查找失败
		}
		return resultData;
	}

	@Override
	public ResultData searchNotice(Notice notice, PageParam pageParam) {
		if(notice != null) {
			if(pageParam != null && pageParam.isPaginate()){
				PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
			}
			List<NoticeDetail> notices = noticeDao.find(notice);
			if(notices.size()>0) {
				Data<List<NoticeDetail>> data = new Data<List<NoticeDetail>>();
				if(pageParam != null && pageParam.isPaginate()){
					PageInfo<NoticeDetail> pageInfo = new PageInfo<>(notices);
					data.setData(pageInfo);
					data.setData(pageInfo.getList());
				} else {
					data.setData(notices);
				}
				resultData.setData(data);
				resultData.setResult(ResultCodeEnum.DB_FIND_SUCCESS); //查找成功
			} else {
				resultData.setResult(ResultCodeEnum.DB_FIND_FAILURE); //查找失败
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //必要请求参数为空
		}
		return resultData;
	}

	@Override
	public ResultData searchFile(File file, PageParam pageParam) {
		if(file != null) {
			if(pageParam != null && pageParam.isPaginate()){
				PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
			}
			List<FileDetail> files = fileDao.find(file);
			if(files.size() > 0) {
				Data<List<FileDetail>> data = new Data<List<FileDetail>>();
				if(pageParam != null && pageParam.isPaginate()){
					PageInfo<FileDetail> pageInfo = new PageInfo<>(files);
					data.setData(pageInfo);
					data.setData(pageInfo.getList());
				} else {
					data.setData(files);
				}
				resultData.setData(data);
				resultData.setResult(ResultCodeEnum.DB_FIND_SUCCESS); //查找成功
			} else {
				resultData.setResult(ResultCodeEnum.DB_FIND_FAILURE); //查找失败
			}
		} else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);  //必要请求参数为空
		}
		return resultData;
	}

	/**
	 * 下载附件
	 * @param dirPath 附件地址
	 * @param attachmentId 附件id
	 * @return 如果查询到了附件，则会返回AttachmentDetail
	 *         如果没有查询到附件，则会返回null
	 */
	@Override
	public AttachmentDetail downloadAttachment(String dirPath, Long attachmentId) {
		if(attachmentId != null){
			Attachment attachment = new Attachment();
			attachment.setId(attachmentId);
			List<Attachment> attachments = attachmentDao.find(attachment);
			if(attachments.size() == 1) {
				AttachmentDetail attachmentDetail = new AttachmentDetail();
				attachmentDetail.setAttachmentInfo(attachments.get(0));

				java.io.File attachmentFile = new java.io.File(dirPath+attachmentDetail.getAttachmentPath());
				attachmentDetail.setFile(attachmentFile);
				System.out.println("attachmentDetail"+attachmentDetail);
				return attachmentDetail;
			} else {
				return null; //未找到对应文件，返回为空
			}
		}
		return null; //确实必要参数返回为空
	}

	@Override
	public ResultData searchAttachment(Attachment attachment, PageParam pageParam) {
		if(attachment != null){
			//是否分页
			if(pageParam != null && pageParam.isPaginate()){
				PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
			}
			List<Attachment> attachments = attachmentDao.find(attachment);
			if(attachments.size() >0){
				Data<List<Attachment>> data = new Data<List<Attachment>>();
				if(pageParam != null && pageParam.isPaginate()){
					PageInfo<Attachment> pageInfo = new PageInfo<>(attachments);
					data.setData(pageInfo);
					data.setData(pageInfo.getList());
				} else {
					data.setData(attachments);
				}
				resultData.setData(data);
				resultData.setResult(ResultCodeEnum.DB_FIND_SUCCESS); //查找成功
			}else {
				resultData.setResult(ResultCodeEnum.DB_FIND_FAILURE); //查找失败
			}
		}else {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL);  //必要请求参数为空
		}
		return resultData;
	}

}
