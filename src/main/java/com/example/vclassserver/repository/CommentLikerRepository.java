package com.example.vclassserver.repository;

import com.example.vclassserver.embedded.vcomment_liker_pk;
import com.example.vclassserver.entity.vcomment_liker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentLikerRepository extends JpaRepository<vcomment_liker,Integer> {

    @Query("select l from vcomment_liker l where l.id=?1")
    List<vcomment_liker> getCommentLikerById(vcomment_liker_pk id);

    @Modifying
    @Transactional
    @Query("delete from vcomment_liker l where l.id=?1")
    void deleteCommentLikerById(vcomment_liker_pk id);

}
