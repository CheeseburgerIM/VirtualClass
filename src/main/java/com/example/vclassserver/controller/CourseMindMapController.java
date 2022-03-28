package com.example.vclassserver.controller;

import com.example.vclassserver.embedded.vcourse_mindMap_pk;
import com.example.vclassserver.entity.vclassuser;
import com.example.vclassserver.entity.vcourse;
import com.example.vclassserver.entity.vcourse_mindMap;
import com.example.vclassserver.repository.CourseMindMapRepository;
import com.example.vclassserver.repository.CourseRepository;
import com.example.vclassserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/course/mindMap/api")
public class CourseMindMapController {

    @Autowired
    CourseMindMapRepository courseMindMapRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping(value = "/addMindMapNode")
    private String addMindMapNode(@RequestParam("teacherUsername") String teacherUsername,
                                  @RequestParam("cname") String cname,
                                  @RequestParam("nodeId") String nodeId,
                                  @RequestParam("isRoot") String isRoot,
                                  @RequestParam("parentId") String parentId,
                                  @RequestParam("topic") String topic,
                                  @RequestParam("direction") String direction,
                                  @RequestParam("expanded") String expanded) {
        List<vclassuser> vclassuserList=userRepository.findUserByUsername(teacherUsername);
        if(vclassuserList.isEmpty()) return "fail"; // 用户不存在
        vclassuser u=vclassuserList.get(0);
        if(!u.getStatus().equals("teacher")) return "fail"; // 用户不是老师
        String cid=teacherUsername+"-"+cname;
        List<vcourse> vcourseList=courseRepository.findCourseById(cid);
        if(vcourseList.isEmpty()) return "fail"; // 课程不存在
        List<vcourse_mindMap> vcourseMindMapList=courseMindMapRepository.findMindMapNodeById(new vcourse_mindMap_pk(cid,nodeId));
        if(!vcourseMindMapList.isEmpty()) return "fail"; // 结点已存在
        vcourse_mindMap m=new vcourse_mindMap(new vcourse_mindMap_pk(cid,nodeId),isRoot,parentId,topic,direction,expanded);
        courseMindMapRepository.save(m);
        return "true";
    }

    @RequestMapping(value = "/getMindMap")
    private Object getMindMap(@RequestParam("teacherUsername") String teacherUsername,
                              @RequestParam("cname") String cname) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        List<vclassuser> vclassuserList=userRepository.findUserByUsername(teacherUsername);
        if(vclassuserList.isEmpty()) return temp; // 用户不存在
        vclassuser u=vclassuserList.get(0);
        if(!u.getStatus().equals("teacher")) return temp; // 用户不是老师
        String cid=teacherUsername+"-"+cname;
        List<vcourse> vcourseList=courseRepository.findCourseById(cid);
        if(vcourseList.isEmpty()) return temp; // 课程不存在
        return courseMindMapRepository.getAllMindMapNodeByCid(cid);
    }

    @RequestMapping(value = "/deleteMindMap")
    private String deleteMindMap(@RequestParam("teacherUsername") String teacherUsername,
                              @RequestParam("cname") String cname) {
        List<vclassuser> vclassuserList=userRepository.findUserByUsername(teacherUsername);
        if(vclassuserList.isEmpty()) return "fail"; // 用户不存在
        vclassuser u=vclassuserList.get(0);
        if(!u.getStatus().equals("teacher")) return "fail"; // 用户不是老师
        String cid=teacherUsername+"-"+cname;
        List<vcourse> vcourseList=courseRepository.findCourseById(cid);
        if(vcourseList.isEmpty()) return "fail"; // 课程不存在
        courseMindMapRepository.deleteAllMindMapNodeByCid(cid);
        return "success";
    }

}
