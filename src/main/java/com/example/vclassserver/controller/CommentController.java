package com.example.vclassserver.controller;

import com.example.vclassserver.embedded.vcomment_liker_pk;
import com.example.vclassserver.entity.vclassuser;
import com.example.vclassserver.entity.vcomment;
import com.example.vclassserver.entity.vcomment_liker;
import com.example.vclassserver.entity.vtopic;
import com.example.vclassserver.repository.CommentLikerRepository;
import com.example.vclassserver.repository.CommentRepository;
import com.example.vclassserver.repository.TopicRepository;
import com.example.vclassserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.example.vclassserver.session.SessionController.sessionMap;

@RestController
@RequestMapping(value = "/comment/api")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    CommentLikerRepository commentLikerRepository;

    @RequestMapping(value = "/addRoot")
    private String addRoot(HttpServletRequest request,
                           @RequestParam("username") String username,
                           @RequestParam("topicId") String topicId,
                           @RequestParam("content") String content,
                           @RequestParam("timestamp") String timestamp) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                vclassuser u=vclassuserList.get(0);
                List<vtopic> vtopicList=topicRepository.getTopicById(topicId);
                if(vtopicList.isEmpty()) return "fail";
                vtopic t=vtopicList.get(0);
                if(u.getStatus().equals("teacher")) t.setIsTeacherReply(t.getIsTeacherReply()+1);
                topicRepository.save(t);
                String commentId= UUID.randomUUID().toString();
                vcomment c=new vcomment(commentId,topicId,username,content,timestamp,"root",null);
                commentRepository.save(c);
                t.setCommentNum(t.getCommentNum()+1);
                topicRepository.save(t);
                return "success";
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/addChild")
    private String addChild(HttpServletRequest request,
                           @RequestParam("username") String username,
                           @RequestParam("topicId") String topicId,
                           @RequestParam("content") String content,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("rootCommentId") String rootCommentId) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                vclassuser u=vclassuserList.get(0);
                List<vtopic> vtopicList=topicRepository.getTopicById(topicId);
                if(vtopicList.isEmpty()) return "fail";
                vtopic t=vtopicList.get(0);
                if(u.getStatus().equals("teacher")) t.setIsTeacherReply(t.getIsTeacherReply()+1);
                topicRepository.save(t);
                String commentId= UUID.randomUUID().toString();
                vcomment c=new vcomment(commentId,topicId,username,content,timestamp,"child",rootCommentId);
                commentRepository.save(c);
                t.setCommentNum(t.getCommentNum()+1);
                topicRepository.save(t);
                return "success";
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/getRoot")
    private Object getRoot(@RequestParam("topicId") String topicId) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        List<vtopic> vtopicList=topicRepository.getTopicById(topicId);
        if(vtopicList.isEmpty()) return temp;
        return commentRepository.getAllRootCommentByTopicIdAndType(topicId,"root");
    }

    @RequestMapping(value = "/getChild")
    private Object getChild(@RequestParam("topicId") String topicId,
                            @RequestParam("rootCommentId") String rootCommentId) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        List<vtopic> vtopicList=topicRepository.getTopicById(topicId);
        if(vtopicList.isEmpty()) return temp;
        return commentRepository.getAllChildCommentByTopicIdAndRootCommentId(topicId,rootCommentId);
    }

    @RequestMapping(value = "/deleteRoot")
    private String deleteRoot() {
        return null;
    }

    @RequestMapping(value = "/deleteChild")
    private String deleteChild() {
        return null;
    }

    @RequestMapping(value = "/likeComment")
    private String likeComment(HttpServletRequest request,
                               @RequestParam("commentId") String commentId,
                               @RequestParam("username") String username) {
        HttpSession session= request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                vclassuser u=vclassuserList.get(0);
                List<vcomment> vcommentList=commentRepository.getCommentByCommentId(commentId);
                if(vcommentList.isEmpty()) return "fail";
                vcomment c=vcommentList.get(0);
                vcomment_liker_pk id=new vcomment_liker_pk(commentId,username);
                List<vcomment_liker> vcommentLikerList=commentLikerRepository.getCommentLikerById(id);
                // 未点赞 则点赞
                if(vcommentLikerList.isEmpty()) {
                    c.setLike(c.getLike()+1);
                    vcomment_liker l=new vcomment_liker(id);
                    commentLikerRepository.save(l);
                }
                // 已点赞 取消赞
                else {
                    c.setLike(c.getLike()-1);
                    commentLikerRepository.deleteCommentLikerById(id);
                }
                commentRepository.save(c);
                return "success";
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/isLikeComment")
    private String isLikeComment(HttpServletRequest request,
                                 @RequestParam("commentId") String commentId,
                                 @RequestParam("username") String username) {
        HttpSession session= request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                vcomment_liker_pk id=new vcomment_liker_pk(commentId,username);
                List<vcomment_liker> vcommentLikerList=commentLikerRepository.getCommentLikerById(id);
                if(vcommentLikerList.isEmpty()) return "false";
                return "true";
            }
            return "fail";
        }
        return "fail";
    }

}
