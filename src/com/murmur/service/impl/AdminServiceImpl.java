package com.murmur.service.impl;

import com.murmur.kit.ResultCodeEnum;
import com.murmur.kit.ResultData;
import com.murmur.mapper.CourseMapper;
import com.murmur.mapper.StudentMapper;
import com.murmur.mapper.TeacherMapper;
import com.murmur.po.Course;
import com.murmur.po.Student;
import com.murmur.po.Teacher;
import com.murmur.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//添加事务处理
@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    private ResultData resultData;

    @Autowired
    private CourseMapper courseDao;

    @Autowired
    private TeacherMapper teacherDao;

    @Autowired
    private StudentMapper studentDao;

    public AdminServiceImpl() {
    }

    @Override
    public ResultData addCourse(Course course) {
        resultData = new ResultData();
        if(course != null) {
            // 插入前进行校验工作，判断约束
            // 课程号唯一
            // 课程学期默认值处理

            if(course.getCourseSemester() == null) {
                course.setCourseSemester("2019-2020-1"); //暂定
            }
            // teacherId 引用的外键，要外键存在才可以插入！否则插入报错！
            if(course.getTeacherId()!= null) {
            	if(!isTeacherExist(course.getTeacherId(),null)) {
            		resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_TEACHER_NOT_EXIST);//添加失败，老师不存在
            		return resultData;
            	}
            }
            // courseType 进行校验工作 1：必修课；2：选修课
            // courseSchool 进行校验工作
            /*
             * 1：信息与安全工程学院；2：马克思主义学院；3：哲学院；4：经济学院；5：财政税务学院；6：金融学院；
             * 7：法学院；8：刑事司法学院；9：外国语学院；10：新闻与文化传播学院；11：工商管理学院；12：文澜学院；
             * 13：会计学院；14：统计与数学学院；15：中韩新媒体学院；
             */
            int result = courseDao.insert(course);
            if(result >0) {
                resultData.setResult(ResultCodeEnum.DB_ADD_SUCCESS);//添加成功
            } else {
                resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE);//添加失败
            }
        } else {
            resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //id直接返回数据必要参数为空
        }

        System.out.println("service:"+resultData);
        return resultData;
    }

    @Override
    public ResultData deleteCourse(Long id) {
        resultData = new ResultData();
        if(id != null) { //如果id不为空
            Course course = new Course();
            course.setId(id);
            int result = courseDao.delete(course);
            if(result > 0) {
                resultData.setResult(ResultCodeEnum.DB_DELETE_SUCCESS); //删除成功
            } else {
                resultData.setResult(ResultCodeEnum.DB_DELETE_FAILURE_COURSE_NOT_EXIST); //删除失败，课程不存在
            }
        } else {
            resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //id直接返回数据必要参数为空
        }

        return resultData;
    }

    @Override
    public ResultData modifyCourse(Course course) {
        resultData = new ResultData();
        if(course != null && course.getId() > 0) { //course不为空且id存在
        	// teacherId 引用的外键，要外键存在才可以插入！否则插入报错！
            if(course.getTeacherId()!= null) {
            	if(!isTeacherExist(course.getTeacherId(),null)) {
            		resultData.setResult(ResultCodeEnum.DB_UPDATE_FAILURE_TEACHER_NOT_EXIST);// 修改失败，老师不存在
            		return resultData;
            	}
            }
            int result = courseDao.update(course);
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
    public ResultData addTeacher(Teacher teacher) {
        resultData = new ResultData();
        if(teacher != null) {
        	//teacher设置teacherNumber唯一
        	if(teacher.getTeacherNumber()!= null) {
            	if(isTeacherExist(null,teacher.getTeacherNumber())) {
            		resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_TEACHER_ALREADY_EXIST);// 添加失败，老师已经存在
            		return resultData;
            	}
            }
            //教师职称和学院校验
            int result = teacherDao.insert(teacher);

            if(result >0) {
                resultData.setResult(ResultCodeEnum.DB_ADD_SUCCESS);//添加成功
            } else {
                resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE);//添加失败
            }
        } else {
            resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //id直接返回数据必要参数为空
        }
        return resultData;
    }

    @Override
    public ResultData deleteTeacher(Long id) {
        resultData = new ResultData();
//        System.out.println(id);
        //级联删除的问题！！！
        if(id != null) {
            Teacher teacher = new Teacher();
            teacher.setId(id);
            int result = teacherDao.delete(teacher);
            if(result > 0) {
                resultData.setResult(ResultCodeEnum.DB_DELETE_SUCCESS); //删除成功
            } else {
                resultData.setResult(ResultCodeEnum.DB_DELETE_FAILURE); //删除失败
            }
        } else {
            resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //id直接返回数据必要参数为空
        }
        return resultData;
    }

    @Override
    public ResultData searchTeacher(Teacher teacher) {
        resultData = new ResultData();
        List<Teacher> teachers = teacherDao.find(teacher);
        if(teachers.size() > 0) {
            resultData.setData(teachers);
            resultData.setResult(ResultCodeEnum.DB_FIND_SUCCESS); //查找成功
        } else {
            resultData.setResult(ResultCodeEnum.DB_FIND_FAILURE);  //查找失败
        }
        return resultData;
    }

    @Override
    public ResultData addStudent(Student student) {
        resultData = new ResultData();
        if(student != null) {
        	//学生学号唯一检验
        	if(isStudentExist(student.getStudentNumber())) {
        		resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_STUDENT_ALREADY_EXIST);//学生已存在
        		return resultData;
        	}
            //教师职称和学院校验
            int result = studentDao.insert(student);
            if(result >0) {
                resultData.setResult(ResultCodeEnum.DB_ADD_SUCCESS);//添加成功
            } else {
                resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE);//添加失败
            }
        } else {
            resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //id直接返回数据必要参数为空
        }
        return resultData;
    }

    @Override
    public ResultData deleteStudent(Long id) {
        resultData = new ResultData();
        System.out.println(id);
        if(id != null) {
            Student student = new Student();
            student.setId(id);
            int result = studentDao.delete(student);
            if(result > 0) {
                resultData.setResult(ResultCodeEnum.DB_DELETE_SUCCESS); //删除成功
            } else {
                resultData.setResult(ResultCodeEnum.DB_DELETE_FAILURE); //删除失败
            }
        } else {
            resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //id直接返回数据必要参数为空
        }
        return resultData;
    }
    
    /**
     * 判断老师是否存在
     * 外键约束条件，若老师不存在插入修改报错
     * @param teacherId
     * @return 存在 true 不存在 false
     */
    private boolean isTeacherExist(Long teacherId,String teacherNumber) {
    	List<Teacher> teachers = null;
        Teacher teacher = new Teacher();
        if(teacherId != null) {
        	teacher.setId(teacherId);
            teachers = teacherDao.find(teacher);
        }else if(teacherNumber != null) {
        	teacher.setTeacherNumber(teacherNumber);
        	teachers = teacherDao.find(teacher);
        }
        if(teacher!= null && teachers.size() == 1) 
        	return true;
        else 
        	return false;
    }
    
    private boolean isStudentExist(String studentNumber) {
    	List<Student> students = null;
    	Student student = new Student();
    	if(studentNumber != null) {
    		student.setStudentNumber(studentNumber);
    		students = studentDao.find(student);
    	}
    	
    	if(student != null && students.size() == 1)
    		return true;
    	else
    		return false;
    }
    


}
