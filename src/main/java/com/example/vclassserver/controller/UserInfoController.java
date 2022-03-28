package com.example.vclassserver.controller;

import com.example.vclassserver.entity.vcourse;
import com.example.vclassserver.entity.vcuser_info;
import com.example.vclassserver.repository.CourseRepository;
import com.example.vclassserver.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.example.vclassserver.session.SessionController.sessionMap;

@RestController
@RequestMapping(value = "/user/api")
public class UserInfoController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    CourseRepository courseRepository;

    @Value("${root}")
    private String root;

    @RequestMapping(value = "/getInfo")
    private Object getInfo(@RequestParam("username") String username,
                           HttpServletRequest request) {
        HttpSession session=request.getSession();
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vcuser_info> vcuserInfoList=userInfoRepository.getUserInfoByUsername(username);
                if(vcuserInfoList.isEmpty()) return temp;
                return userInfoRepository.getUserInfoByUsername(username).get(0);
            }
            else return temp;
        }
        return temp;
    }

    @RequestMapping(value = "/updateInfo")
    private String uploadInfo(HttpServletRequest request,
                              @RequestParam("username") String username,
                              @RequestParam("link") String link,
                              @RequestParam("grade") String grade,
                              @RequestParam("address") String address,
                              @RequestParam("status") String status,
                              @RequestParam("name") String name,
                              @RequestParam("subtitle") String subtitle) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                String avatarPath="//VClass//"+status+"//"+username+"//"+"avatar.jpg";
                List<vcuser_info> list=userInfoRepository.getUserInfoByUsername(username);
                if(list.isEmpty()) {
                    vcuser_info i=new vcuser_info(username,link,grade,address,status,0,0,name,subtitle,avatarPath);
                    userInfoRepository.save(i);
                    return "success";
                }
                else {
                    if(status.equals("teacher")) {
                        List<vcourse> vcourseList=courseRepository.findAllCourseByUsername(username);
                        Iterator<vcourse> iterator=vcourseList.iterator();
                        while(iterator.hasNext()) {
                            iterator.next().setTeacher(name);
                        }
                    }
                    vcuser_info i=list.get(0);
                    i.setLink(link);
                    i.setGrade(grade);
                    i.setAddress(address);
                    i.setFollowers(0);
                    i.setFollowing(0);
                    i.setName(name);
                    i.setSubtitle(subtitle);
                    i.setAvatar(avatarPath);
                    userInfoRepository.save(i);
                    return "success";
                }
            }
            else return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/getAvatar")
    private String getAvatar(HttpServletRequest request,
                             @RequestParam("username") String username) {
        HttpSession session=request.getSession();
//        if(sessionMap.containsKey(session.getId())) {
        if(true) {
//            if(session.getAttribute(username)!=null) {
            if(true) {
                List<vcuser_info> list=userInfoRepository.getUserInfoByUsername(username);
                if(list.isEmpty()) return "//VClass//static//defaultAvatar.jpg";
                else {
                    vcuser_info i=list.get(0);
                    String avatarPath=root+"//VClass//"+i.getStatus()+"//"+username+"//"+"avatar.jpg";
                    File file=new File(avatarPath);
                    if(file.exists()) return "//VClass//"+i.getStatus()+"//"+username+"//"+"avatar.jpg";
                    return "//VClass//static//defaultAvatar.jpg";
                }
            }
            else return "fail";
        }
        else return "fail";
    }

    @RequestMapping(value = "/setAvatar")
    private String setAvatar(HttpServletRequest request,
                             @RequestParam("username") String username,
                             @RequestParam("status") String status,
                             @RequestParam("avatar") MultipartFile avatar) throws IOException {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                String avatarPath=new File(root).getAbsolutePath()+"//VClass//"+status+"//"+username+"//avatar.jpg";
                File file=new File(avatarPath);
                if(!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                avatar.transferTo(file);
                return "success";
            }
            else return "fail";
        }
        return "fail";

//        String avatarPath=root+"//VClass//"+status+"//"+username+"//avatar.jpg";
//        File file=new File(avatarPath);
//        if(!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        avatar.transferTo(file);
//        return "success";

    }

}
