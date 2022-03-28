package com.example.vclassserver.repository;

import com.example.vclassserver.entity.vtopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<vtopic,Integer> {

    @Query("select t from vtopic t where t.fid=?1 order by t.timestamp")
    List<vtopic> getAllTopicByFid(String fid);

    @Query("select t from vtopic t where t.topicId=?1")
    List<vtopic> getTopicById(String topicId);

}
