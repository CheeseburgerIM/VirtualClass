package com.example.vclassserver.repository;

import com.example.vclassserver.entity.vcomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<vcomment,Integer> {

    @Query("select c from vcomment c where c.topicId=?1 and c.type=?2 order by c.timestamp")
    List<vcomment> getAllRootCommentByTopicIdAndType(String topicId,String type);

    @Query("select c from vcomment c where c.topicId=?1 and c.rootCommentId=?2 order by c.timestamp")
    List<vcomment> getAllChildCommentByTopicIdAndRootCommentId(String topicId,String rootCommentId);

    @Query("select c from vcomment c where c.commentId=?1")
    List<vcomment> getCommentByCommentId(String commentId);

}
