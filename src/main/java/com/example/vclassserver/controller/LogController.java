package com.example.vclassserver.controller;

import com.example.vclassserver.entity.vclog;
import com.example.vclassserver.entity.vcourse;
import com.example.vclassserver.entity.vcuser_info;
import com.example.vclassserver.repository.CourseRepository;
import com.example.vclassserver.repository.LogRepository;
import com.example.vclassserver.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/log/api")
public class LogController {

    @Autowired
    LogRepository logRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping(value = "/addLog")
    private String addLog(@RequestParam("username") String username,
                          @RequestParam("teacherUsername") String teacherUsername,
                          @RequestParam("cname") String cname,
                          @RequestParam("activity") String activity,
                          @RequestParam("fname") String fname,
                          @RequestParam("timestamp") String timestamp) {
        String lid=username+"-"+timestamp;
        String cid=teacherUsername+"-"+cname;
        List<vclog> vclogList=logRepository.findLogByContent(lid);
        List<vcuser_info> vcuserInfoList=userInfoRepository.getUserInfoByUsername(username);
        System.out.println(vcuserInfoList);
        List<vcourse> vcourseList=courseRepository.findCourseById(cid);
        if(vcourseList.isEmpty()) return "fail";
        if(vcuserInfoList.isEmpty()) return "fail";
        String name=vcuserInfoList.get(0).getName();
        if(vclogList.isEmpty()) {
            vclog log=new vclog(lid,cid,name,activity,fname,timestamp);
            logRepository.save(log);
            return "success";
        }
        return "fail";
    }

    @RequestMapping(value = "/getAllLog")
    private Object getAllLog(@RequestParam("teacherUsername") String teacherUsername,
                             @RequestParam("cname") String cname) {
        String cid=teacherUsername+"-"+cname;
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        return logRepository.findAllLogOrderByTimeDesc(cid);
    }

    @RequestMapping(value = "/getTopHundredLog")
    private Object getTopHundredLog(@RequestParam("teacherUsername") String teacherUsername,
                                    @RequestParam("cname") String cname) {
        String cid=teacherUsername+"-"+cname;
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        return logRepository.findTopHundredLogOrderByTimeDesc(cid);
    }

}
