package com.example.vclassserver.controller;

import com.example.vclassserver.embedded.vcourse_subscribe_pk;
import com.example.vclassserver.entity.vclassuser;
import com.example.vclassserver.entity.vcourse;
import com.example.vclassserver.entity.vcourse_subscribe;
import com.example.vclassserver.entity.vcuser_info;
import com.example.vclassserver.repository.CourseRepository;
import com.example.vclassserver.repository.SubscribeRepository;
import com.example.vclassserver.repository.UserInfoRepository;
import com.example.vclassserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.example.vclassserver.session.SessionController.sessionMap;

@RestController
@RequestMapping(value = "/course/api")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    SubscribeRepository subscribeRepository;

    @RequestMapping(value = "/updateCourseInfo")
    private String createCourse(HttpServletRequest request,
                                @RequestParam("username") String username,
                                @RequestParam("cname") String cname,
                                @RequestParam("ctype") String ctype,
                                @RequestParam("cdesc") String cdesc,
                                @RequestParam("credit") int credit,
                                @RequestParam("duration") int duration) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                vclassuser u=vclassuserList.get(0);
                if(!u.getStatus().equals("teacher")) return "fail";
                List<vcourse> list=courseRepository.findCourseById(username+"-"+cname);
                List<vcourse> vcourseList=courseRepository.findAllCourseByUsername(username);
                List<vcuser_info> vcuserInfoList=userInfoRepository.getUserInfoByUsername(username);
                if(list.isEmpty()) {
                    String cnum=username+"-"+String.valueOf(vcourseList.size()+1);
                    vcourse c = new vcourse(username + "-" +cname, username,cname, ctype,cdesc,credit,cnum,duration,vcuserInfoList.get(0).getName());
                    courseRepository.save(c);
                    return "success";
                }
                else {
                    vcourse c=list.get(0);
                    c.setCtype(ctype);
                    c.setCdesc(cdesc);
                    c.setCredit(credit);
                    c.setDuration(duration);
                    courseRepository.save(c);
                    return "success";
                }
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/getCourseInfo")
    private Object getCourseInfo(HttpServletRequest request,
                                 @RequestParam("username") String username,
                                 @RequestParam("cname") String cname) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        HttpSession session=request.getSession();
//        if(sessionMap.containsKey(session.getId())) {
        if(true) {
//            if(session.getAttribute(username)!=null) {
            if(true) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                if(!vclassuserList.get(0).getStatus().equals("teacher")) return temp;
                String cid=username+"-"+cname;
                List<vcourse> list=courseRepository.findCourseById(cid);
                if(list.isEmpty()) {
                    return temp;
                }
                return list.get(0);
            }
            return temp;
        }
        return temp;
    }

    @RequestMapping(value = "/getAllCourseByTeacherusername")
    private Object getAllCourseByTeacherusername(HttpServletRequest request,
                                                        @RequestParam("username") String username) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return temp;
                return courseRepository.findAllCourseByUsername(username);
            }
            return temp;
        }
        return temp;
    }

    @RequestMapping(value = "/getAllCourseByStudentusername")
    private Object getAllCourseStudentusername(HttpServletRequest request,
                                                      @RequestParam("username") String username) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return temp;
                vclassuser u=vclassuserList.get(0);
                if(!u.getStatus().equals("student")) return temp;
                List<vcourse_subscribe> vcourseSubscribeList=subscribeRepository.findAllCourseByStudentusername(username);
                if(vcourseSubscribeList.isEmpty()) return temp;
                List<vcourse> studentCourseList=new ArrayList<>();
                Iterator<vcourse_subscribe> iterator=vcourseSubscribeList.iterator();
                while(iterator.hasNext()) {
                    vcourse_subscribe s=iterator.next();
                    String cid=s.getId().getCid();
                    vcourse c=courseRepository.findCourseById(cid).get(0);
                    studentCourseList.add(c);
                }
                return studentCourseList;
            }
            return temp;
        }
        return temp;
    }

    @RequestMapping(value = "/getAllCourse")
    private List<vcourse> getAllCourse() {
        return courseRepository.findAll();
    }

    @RequestMapping(value = "/subscribe")
    private String subscribe(HttpServletRequest request,
                             @RequestParam("studentUsername") String studentUsername,
                             @RequestParam("teacherUsername") String teacherUsername,
                             @RequestParam("cname") String cname) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(studentUsername)!=null) {
                String cid=teacherUsername+"-"+cname;
                vcourse_subscribe_pk pk=new vcourse_subscribe_pk(cid,studentUsername);
                List<vcourse_subscribe> vcourseSubscribeList=subscribeRepository.findSubscribeById(pk);
                if(!vcourseSubscribeList.isEmpty()) return "done";
                vcourse_subscribe sub=new vcourse_subscribe(pk);
                subscribeRepository.save(sub);
                return "success";
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/getAllSubscriber")
    private Object getAllSubscriber(@RequestParam("username") String username,
                                    @RequestParam("cname") String cname) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
        if(vclassuserList.isEmpty()) return temp;
        vclassuser u=vclassuserList.get(0);
        if(!u.getStatus().equals("teacher")) return temp;
        String cid=username+"-"+cname;
        return subscribeRepository.findAllSubscriberByCourseId(cid);
    }

    @RequestMapping(value = "/search")
    private Object search(@RequestParam("keyword") String keyword) {
        Set<vcourse> result=new HashSet<>();
        result.addAll(courseRepository.findCourseById(keyword));
        result.addAll(courseRepository.findAllCourseByUsername(keyword));
        result.addAll(courseRepository.findAllCourseByCourseName(keyword));
        result.addAll(courseRepository.findAllCourseByTeacherName(keyword));
        return result;
    }

}
