package com.coffee.service.impl;

import com.coffee.mapper.CourseScheduleMapper;
import com.coffee.po.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.coffee.kit.Data;
import com.coffee.kit.ResultCodeEnum;
import com.coffee.kit.ResultData;
import com.coffee.mapper.CourseMapper;
import com.coffee.mapper.StudentMapper;
import com.coffee.mapper.TeacherMapper;
import com.coffee.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//添加事务处理
@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    private ResultData<Data> resultData;

    @Autowired
    private CourseMapper courseDao;

    @Autowired
    private TeacherMapper teacherDao;

    @Autowired
    private StudentMapper studentDao;

    @Autowired
    private CourseScheduleMapper courseScheduleDao;

    public AdminServiceImpl() {
        resultData = new ResultData<Data>();
    }

    @Override
    public ResultData addCourse(Course course) {
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
    public ResultData searchTeacher(Teacher teacher, PageParam pageParam) {
        if(pageParam != null && pageParam.isPaginate()){//是否分页
            PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        }
        List<Teacher> teachers = teacherDao.find(teacher);
        if(teachers.size() > 0) {
            Data<List<Teacher>> data = new Data<List<Teacher>>();
            if(pageParam != null && pageParam.isPaginate()){
                PageInfo<Teacher> pageInfo = new PageInfo<>(teachers);
                data.setData(pageInfo);
                data.setData(pageInfo.getList());
            } else {
                data.setData(teachers);
            }
            resultData.setData(data);
            resultData.setResult(ResultCodeEnum.DB_FIND_SUCCESS); //查找成功
        } else {
            resultData.setResult(ResultCodeEnum.DB_FIND_FAILURE);  //查找失败
        }
        return resultData;
    }

    @Override
    public ResultData addStudent(Student student) {
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
     * @param teacherId 教师id
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
        return teachers != null && teachers.size() == 1;
    }
    
    private boolean isStudentExist(String studentNumber) {
    	List<Student> students = null;
    	Student student = new Student();
    	if(studentNumber != null) {
    		student.setStudentNumber(studentNumber);
    		students = studentDao.find(student);
    	}

        return students != null && students.size() == 1;
    }

    @Override
    public ResultData searchTeacherByKey(String searchKey) {
        List<Teacher> teachers = teacherDao.search(searchKey);
        if(teachers.size() > 0) {
            Data<List<Teacher>> data = new Data<>();
            data.setData(teachers);
            resultData.setData(data);
            resultData.setResult(ResultCodeEnum.DB_FIND_SUCCESS); //查找成功
        } else {
            resultData.setResult(ResultCodeEnum.DB_FIND_FAILURE);  //查找失败
        }
        return resultData;
    }

    @Override
    public ResultData addCourseSchedule(List<Long> studentId, List<Long> courseId) {
        if(studentId == null || courseId == null){
            resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //重要请求参数为空
            return resultData;
        }
        //判断，是为学生添加老师还是为老师添加学生

        int result;
        if(courseId.size() == 1) {
            // 为一个课程添加多个学生
            //课程的外键检验
            if(!isCourseExist(courseId.get(0))){
                resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_COURSE_NOT_EXIST);//课程不存在
                return resultData;
            }
            for(Long student:studentId){
                //学生的外键检验
                if(!isStudentExist(student)){
                    resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_STUDENT_NOT_EXIST);//学生不存在
                    return resultData;
                }
                result = courseScheduleDao.insert(new CourseSchedule(student,courseId.get(0)));
                if(result <= 0) {
                    resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE);
                    return resultData;
                }
            }
            resultData.setResult(ResultCodeEnum.DB_ADD_SUCCESS);
        } else if(studentId.size() == 1) {
            //为一个学生添加多个课程
            //学生的外键检验
            if(!isStudentExist(studentId.get(0))){
                resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_STUDENT_NOT_EXIST);//学生不存在
                return resultData;
            }
            for(Long course:courseId){
                //课程的外键检验
                if(!isCourseExist(course)){
                    resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE_COURSE_NOT_EXIST);//课程不存在
                    return resultData;
                }
                result = courseScheduleDao.insert(new CourseSchedule(studentId.get(0),course));
                if(result <= 0) {
                    resultData.setResult(ResultCodeEnum.DB_ADD_FAILURE);
                    return resultData;
                }
            }
            resultData.setResult(ResultCodeEnum.DB_ADD_SUCCESS);
        } else {
            resultData.setResult(ResultCodeEnum.PARA_FORMAT_ERROR); //请求参数格式错误
        }
        return resultData;
    }

    @Override
    public ResultData deleteCourseSchedule(Long id) {
        if(id != null){
            int result = courseScheduleDao.delete(new CourseSchedule(id));
            if(result > 0) {
                resultData.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            } else {
                resultData.setResult(ResultCodeEnum.DB_DELETE_FAILURE);
            }
        } else {
            resultData.setResult(ResultCodeEnum.PARA_WORNING_NULL); //重要请求参数为空
        }
        return resultData;
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

    /**
     * 判断学生是否存在
     * 外键约束条件
     * @param studentId 学生id
     * @return
     */
    private boolean isStudentExist(Long studentId) {
        List<Student> students = null;
        Student student = new Student();
        if(studentId != null) {
            student.setId(studentId);
            students = studentDao.find(student);
        }
        if(students!= null && students.size() == 1)
            return true;
        else
            return false;
    }
}