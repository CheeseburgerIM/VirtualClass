package com.example.vclassserver.repository;

import com.example.vclassserver.embedded.vcuser_activity_pk;
import com.example.vclassserver.entity.vcuser_activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserActivityReposotory extends JpaRepository<vcuser_activity,Integer> {

    @Query("select a from vcuser_activity a where a.id.username=?1 AND a.id.date>?2")
    List<vcuser_activity> getAllActByUsername(String username, String date);

    @Query("select a from vcuser_activity a where a.id=?1")
    List<vcuser_activity> findActById(vcuser_activity_pk id);

}
