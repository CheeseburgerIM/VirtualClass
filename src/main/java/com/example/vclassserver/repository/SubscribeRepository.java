package com.example.vclassserver.repository;

import com.example.vclassserver.embedded.vcourse_subscribe_pk;
import com.example.vclassserver.entity.vcourse_subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<vcourse_subscribe,Integer> {

    @Query("select s from vcourse_subscribe s where s.id=?1")
    List<vcourse_subscribe> findSubscribeById(vcourse_subscribe_pk pk);

    @Query("select s from vcourse_subscribe s where s.id.cid=?1")
    List<vcourse_subscribe> findAllSubscriberByCourseId(String cid);

    @Query("select s from vcourse_subscribe s where s.id.username=?1")
    List<vcourse_subscribe> findAllCourseByStudentusername(String username);

}
