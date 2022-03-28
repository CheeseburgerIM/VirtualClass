package com.example.vclassserver.repository;

import com.example.vclassserver.embedded.vtopic_liker_pk;
import com.example.vclassserver.entity.vtopic_liker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TopicLikerRepository extends JpaRepository<vtopic_liker,Integer> {

    @Query("select l from vtopic_liker l where l.id=?1")
    List<vtopic_liker> getTopicLikerById(vtopic_liker_pk id);

    @Modifying
    @Transactional
    @Query("delete from vtopic_liker l where l.id=?1")
    void deleteTopicLikerById(vtopic_liker_pk id);

}
