package com.example.vclassserver.controller;

import com.example.vclassserver.entity.vclassuser;
import com.example.vclassserver.entity.vcourse;
import com.example.vclassserver.entity.vcourse_chapter;
import com.example.vclassserver.repository.ChapterRepository;
import com.example.vclassserver.repository.CourseRepository;
import com.example.vclassserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.vclassserver.session.SessionController.sessionMap;

@RestController
@RequestMapping(value = "/course/chapter/api")
public class ChapterController {

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/updateChapter")
    private String createChapter(HttpServletRequest request,
                                 @RequestParam("username") String username,
                                 @RequestParam("cname") String cname,
                                 @RequestParam("chname") String chname,
                                 @RequestParam("chnum") int chnum,
                                 @RequestParam("chtag") String chtag) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                vclassuser u= vclassuserList.get(0);
                if(!u.getStatus().equals("teacher")) return "fail";
                List<vcourse> vcourseList=courseRepository.findCourseById(username+"-"+cname);
                if(vcourseList.isEmpty()) return "fail";
                vcourse c=vcourseList.get(0);
                String chid=username+"-"+cname+"-"+chname;
                List<vcourse_chapter> vcourseChapterList=chapterRepository.findChapterById(chid);
                if(vcourseChapterList.isEmpty()) {
                    vcourse_chapter ch=new vcourse_chapter(chid,chname,chnum,chtag,c);
                    chapterRepository.save(ch);
                    return "success";
                }
                vcourse_chapter ch=vcourseChapterList.get(0);
                ch.setChname(chname);
                ch.setChnum(chnum);
                ch.setChtag(chtag);
                chapterRepository.save(ch);
                return "success";
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/deleteAllChapter")
    private String deleteAllChapter(HttpServletRequest request,
                                    @RequestParam("username") String username,
                                    @RequestParam("cname") String cname) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return "fail";
                vclassuser u=vclassuserList.get(0);
                if(!u.getStatus().equals("teacher")) return "fail";
                List<vcourse> vcourseList=courseRepository.findCourseById(username+"-"+cname);
                if(vcourseList.isEmpty()) return "fail";
                chapterRepository.deleteAllChapterByCourseId(username+"-"+cname);
                return "success";
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/getAllChapter")
    private List<vcourse_chapter> getAllChapter(HttpServletRequest request,
                                                @RequestParam("username") String username,
                                                @RequestParam("cname") String cname) {
        HttpSession session=request.getSession();
//        if(sessionMap.containsKey(session.getId())) {
        if(true) {
//            if(session.getAttribute(username)!=null) {
            if(true) {
                List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
                if(vclassuserList.isEmpty()) return null;
                vclassuser u=vclassuserList.get(0);
                if(!u.getStatus().equals("teacher")) return null;
                List<vcourse> vcourseList=courseRepository.findCourseById(username+"-"+cname);
                if(vcourseList.isEmpty()) return null;
                return chapterRepository.findAllChapterByCourseId(username+"-"+cname);
            }
            return null;
        }
        return null;
    }

}
