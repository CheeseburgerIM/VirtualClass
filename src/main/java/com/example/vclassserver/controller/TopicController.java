package com.example.vclassserver.controller;

import com.example.vclassserver.embedded.vtopic_liker_pk;
import com.example.vclassserver.entity.*;
import com.example.vclassserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.example.vclassserver.session.SessionController.sessionMap;

@RestController
@RequestMapping(value = "/topic/api")
public class TopicController {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    TopicLikerRepository topicLikerRepository;

    @Value("${root}")
    private String root;

    @RequestMapping(value = "/addTopicId")
    private String addTopicId(HttpServletRequest request,
                              @RequestParam("username") String username) {
        HttpSession session= request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                return UUID.randomUUID().toString();
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/addTopicWithCover") // add topic with cover
    private String addTopicWithCover(HttpServletRequest request,
                     @RequestParam("topicId") String topicId,
                     @RequestParam("username") String username,
                     @RequestParam("fid") String fid,
                     @RequestParam("content") String content,
                     @RequestParam("timestamp") String timestamp,
                     @RequestParam("title") String title,
                     @RequestParam("description") String description,
                     @RequestParam("coverImage") MultipartFile coverImage) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                else {
                    vclassuser u=vclassuserList.get(0);
                    String cover = null;
                    if (!coverImage.isEmpty()) {
                        cover="//VClass//"+u.getStatus()+"//"+username+"//topic//"+topicId+"//cover//topicCover.jpg";
                        File file = new File(root + cover);
                        if(!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        try {
                            coverImage.transferTo(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    String userAvatar = null;
                    List<vcuser_info> vcuserInfoList=userInfoRepository.getUserInfoByUsername(username);
                    if(!vcuserInfoList.isEmpty()) userAvatar=vcuserInfoList.get(0).getAvatar();
                    vtopic t=new vtopic(topicId,username,fid,content,timestamp,title,description,cover,userAvatar);
                    topicRepository.save(t);
                    return "success";
                }
            }
        }
        return "fail";
    }

    @RequestMapping(value = "/addTopicWithoutCover") // add topic without cover
    private String addTopicWithoutCover(HttpServletRequest request,
                            @RequestParam("topicId") String topicId,
                            @RequestParam("username") String username,
                            @RequestParam("fid") String fid,
                            @RequestParam("content") String content,
                            @RequestParam("timestamp") String timestamp,
                            @RequestParam("title") String title,
                            @RequestParam("description") String description) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                else {
                    String userAvatar = null;
                    List<vcuser_info> vcuserInfoList=userInfoRepository.getUserInfoByUsername(username);
                    if(!vcuserInfoList.isEmpty()) userAvatar=vcuserInfoList.get(0).getAvatar();
                    vtopic t=new vtopic(topicId,username,fid,content,timestamp,title,description,null,userAvatar);
                    topicRepository.save(t);
                    return "success";
                }
            }
        }
        return "fail";
    }

    @RequestMapping(value = "/getAllTopics")
    private Object getAllTopics(@RequestParam("fid") String fid) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        List<vcfile> vcfileList= fileRepository.getFileById(fid);
        if(!vcfileList.isEmpty()) return topicRepository.getAllTopicByFid(fid);
        return temp;
    }

    @RequestMapping(value = "/getSingleTopic")
    private Object getSingleTopic(@RequestParam("topicId") String topicId) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        List<vtopic> vtopicList=topicRepository.getTopicById(topicId);
        if(vtopicList.isEmpty()) return temp;
        return vtopicList.get(0);
    }

    @RequestMapping(value = "/likeTopic")
    private String likeTopic(HttpServletRequest request,
                             @RequestParam("topicId") String topicId,
                             @RequestParam("username") String username) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                List<vtopic> vtopicList=topicRepository.getTopicById(topicId);
                vtopic t=vtopicList.get(0);
                if(vtopicList.isEmpty()) return "fail";
                else {
                    vtopic_liker_pk id=new vtopic_liker_pk(topicId,username);
                    List<vtopic_liker> vtopicLikerList=topicLikerRepository.getTopicLikerById(id);
                    // 未点赞 则点赞
                    if(vtopicLikerList.isEmpty()) {
                        vtopic_liker l=new vtopic_liker(id);
                        t.setLikeNum(t.getLikeNum()+1);
                        topicRepository.save(t);
                        topicLikerRepository.save(l);
                    }
                    // 已点赞 取消赞
                    else {
                        vtopic_liker l=vtopicLikerList.get(0);
                        t.setLikeNum(t.getLikeNum()-1);
                        topicRepository.save(t);
                        topicLikerRepository.deleteTopicLikerById(id);
                    }
                    return "success";
                }
            }
        }
        return "fail";
    }

    @RequestMapping(value = "/isLikeTopic")
    private String isLikeTopic(HttpServletRequest request,
                               @RequestParam("topicId") String topicId,
                               @RequestParam("username") String username) {
        HttpSession session= request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                vtopic_liker_pk id=new vtopic_liker_pk(topicId,username);
                List<vtopic_liker> vtopicLikerList=topicLikerRepository.getTopicLikerById(id);
                if(vtopicLikerList.isEmpty()) return "false";
                else return "true";
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/uploadTopicImage")
    private String uploadTopicImage(HttpServletRequest request,
                                    @RequestParam("topicId") String topicId,
                                    @RequestParam("username") String username,
                                    @RequestParam("image") MultipartFile image) {
        HttpSession session= request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                vclassuser u=vclassuserList.get(0);
                String path="//VClass//"+u.getStatus()+"//"+username+"//topic//"+topicId+"//content//"+UUID.randomUUID().toString()+".jpg";
                File file=new File(root+path);
                if(!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                try {
                    image.transferTo(file);
                    return path;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "fail";
        }
        return "fail";
    }

}
