package com.trick.biz.mvc.student.service;

import com.trick.biz.mvc.student.model.Student;
import java.util.List;
import java.util.Map;

public interface StudentService {
    int saveStudent(Student student);

    int removeStudent(int id);

    int updateStudent(Student student);

    String listPageStudent(Map<String, Object> param);

    List<Map<String, Object>> listDataStudent(Map<String, Object> param);

    Student getStudent(int id);
}