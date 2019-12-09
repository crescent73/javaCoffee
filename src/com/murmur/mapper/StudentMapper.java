package com.murmur.mapper;

import com.murmur.po.Student;

import java.util.List;

public interface StudentMapper {
    public List<Student> find(Student student);
    public int update(Student student);
    public int delete(Student student);
    public int insert(Student student);
}
