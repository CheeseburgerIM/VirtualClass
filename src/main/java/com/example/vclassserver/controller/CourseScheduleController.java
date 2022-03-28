package com.example.vclassserver.controller;

import com.example.vclassserver.embedded.vcourse_schedule_pk;
import com.example.vclassserver.entity.vclassuser;
import com.example.vclassserver.entity.vcourse_schedule;
import com.example.vclassserver.repository.CourseScheduleRepository;
import com.example.vclassserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/course/schedule/api")
public class CourseScheduleController {

    @Autowired
    CourseScheduleRepository courseScheduleRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/getSchedule")
    private Object getSchedule(@RequestParam("username") String username,
                               @RequestParam("cname") String cname) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
        if(vclassuserList.isEmpty()) return temp;
        if(!vclassuserList.get(0).getStatus().equals("teacher")) return temp;
        String cid=username+"-"+cname;
        return courseScheduleRepository.getScheduleByCid(cid);
    }

    @RequestMapping(value = "/setSchedule")
    private String setSchedule(@RequestParam("username") String username,
                               @RequestParam("cname") String cname,
                               @RequestParam("date") String date,
                               @RequestParam("type") String type,
                               @RequestParam("content") String content) {
        List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
        if(vclassuserList.isEmpty()) return "fail";
        if(!vclassuserList.get(0).getStatus().equals("teacher")) return "fail";
        String cid=username+"-"+cname;
        vcourse_schedule_pk id=new vcourse_schedule_pk(cid,date);
        List<vcourse_schedule> vcourseScheduleList=courseScheduleRepository.findScheduleById(id);
        if(!vcourseScheduleList.isEmpty()) return "fail";
        vcourse_schedule s=new vcourse_schedule(id,type,content);
        courseScheduleRepository.save(s);
        return "success";
    }

    @RequestMapping(value = "/deleteSchedule")
    private String deleteSchedule(@RequestParam("username") String username,
                                  @RequestParam("cname") String cname) {
        List<vclassuser> vclassuserList=userRepository.findUserByUsername(username);
        if(vclassuserList.isEmpty()) return "fail";
        if(!vclassuserList.get(0).getStatus().equals("teacher")) return "fail";
        String cid=username+"-"+cname;
        courseScheduleRepository.deleteScheduleByCid(cid);
        return "success";
    }

}
