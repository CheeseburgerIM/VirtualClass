package com.example.vclassserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.vclassserver.entity.vcourse;
import java.util.List;

public interface CourseRepository extends JpaRepository<vcourse,Integer> {

    @Query("select c from vcourse c where c.cid=?1")
    List<vcourse> findCourseById(String id);

    @Query("select c from vcourse c where c.username=?1")
    List<vcourse> findAllCourseByUsername(String username);

    @Query("select c from vcourse c where c.cname=?1")
    List<vcourse> findAllCourseByCourseName(String cname);

    @Query("select c from vcourse c where c.teacher=?1")
    List<vcourse> findAllCourseByTeacherName(String teacherName);

}
