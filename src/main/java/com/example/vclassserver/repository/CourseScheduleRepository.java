package com.example.vclassserver.repository;

import com.example.vclassserver.embedded.vcourse_schedule_pk;
import com.example.vclassserver.entity.vcourse_schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseScheduleRepository extends JpaRepository<vcourse_schedule,Integer> {

    @Query("select s from vcourse_schedule s where s.id.cid=?1 order by s.id.date")
    List<vcourse_schedule> getScheduleByCid(String cid);

    @Query("select s from vcourse_schedule s where s.id=?1")
    List<vcourse_schedule> findScheduleById(vcourse_schedule_pk id);

    @Transactional
    @Modifying
    @Query("delete from vcourse_schedule where id.cid=?1")
    void deleteScheduleByCid(String cid);

}
