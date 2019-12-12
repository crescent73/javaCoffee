package com.coffee.mapper;

import com.coffee.po.Teacher;

import java.util.List;

public interface TeacherMapper {
    public List<Teacher> find(Teacher teacher);
    public int update(Teacher teacher);
    public int delete(Teacher teacher);
    public int insert(Teacher teacher);
}
