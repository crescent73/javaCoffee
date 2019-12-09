package com.murmur.service.impl;

import java.util.List;

import com.murmur.kit.Data;
import com.murmur.kit.ResultCodeEnum;
import com.murmur.kit.ResultData;
import com.murmur.mapper.*;
import com.murmur.po.*;
import com.murmur.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SystemServiceImpl implements SystemService {
	
	private ResultData resultData;
	
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

	public SystemServiceImpl() {
		resultData = new ResultData();
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
				data.setLength(result.size());
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
	public ResultData searchCourse(Long studentId, Course course) {
		if(course == null) {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //必要请求参数为空
			return resultData;
		}
		System.out.println("studentId:"+studentId+",course:"+course);
		List<CourseDetail> courses;
		if(studentId != null) {//查询学生的课程列表
			courses = courseScheduleDao.findCourseByStudentId((long)(studentId));
		} else {
			courses = courseDao.find(course);
		}
		System.out.println(courses);
		if(courses.size() > 0) {
			Data<List<CourseDetail>> data = new Data<List<CourseDetail>>();
			data.setData(courses);
			data.setLength(courses.size());
			resultData.setData(data);
			resultData.setResult(ResultCodeEnum.DB_FIND_SUCCESS); //查找成功
		} else {
			resultData.setResult(ResultCodeEnum.DB_FIND_FAILURE); //查找失败
		}
		return resultData;
	}

	@Override
	public ResultData searchStudent(Long courseId, Student student) {
		if(student == null) {
			resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //必要请求参数为空
			return resultData;
		}
		List<Student> students;
		if(courseId != null) {//查询某课程下的学生列表
			students = courseScheduleDao.findStudentByCourseId(courseId);
		} else {
			students = studentDao.find(student);
		}
		System.out.println(students);
		if(students.size() > 0) {
			Data<List<Student>> data = new Data<List<Student>>();
			data.setData(students);
			resultData.setData(data);
			resultData.setResult(ResultCodeEnum.DB_FIND_SUCCESS); //查找成功
		} else {
			resultData.setResult(ResultCodeEnum.DB_FIND_FAILURE); //查找失败
		}
		return resultData;
	}

	@Override
	public ResultData searchNotice(Notice notice) {
		if(notice != null) {
			List<NoticeDetail> notices = noticeDao.find(notice);
			if(notices.size()>0) {
				Data<List<NoticeDetail>> data = new Data<List<NoticeDetail>>();
				data.setData(notices);
				data.setLength(notices.size());
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
	public ResultData searchFile(File file) {
		if(file != null) {		
			List<FileDetail> files = fileDao.find(file);
			if(files.size() > 0) {
				Data<List<FileDetail>> data = new Data<List<FileDetail>>();
				data.setData(files);
				data.setLength(files.size());
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

}
