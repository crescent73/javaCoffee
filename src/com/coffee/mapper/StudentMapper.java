package com.coffee.mapper;

import com.coffee.po.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    public List<Student> find(Student student);
    public int update(Student student);
    public int delete(Student student);
    public int insert(Student student);

    /**
     * 搜索字段： 学生姓名 学号 班级
     * @param key 搜索词
     * @return 结果
     */
    public List<Student> search(@Param("student") Student student, @Param("key") String key);
}
