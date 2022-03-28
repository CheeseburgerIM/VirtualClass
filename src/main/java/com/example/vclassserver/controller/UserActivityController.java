package com.example.vclassserver.controller;

import com.example.vclassserver.embedded.vcuser_activity_pk;
import com.example.vclassserver.entity.vcuser_activity;
import com.example.vclassserver.repository.UserActivityReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.vclassserver.session.SessionController.sessionMap;

@RestController
@RequestMapping(value = "/user/api")
public class UserActivityController {

    @Autowired
    UserActivityReposotory userActivityReposotory;

    @RequestMapping(value = "/getAct")
    private Object getAct(@RequestParam("username") String username,
                          HttpServletRequest request) {
        Map<String,String> temp=new HashMap<>();
        temp.put("result","fail");
        Date mDate=new Date();
        mDate.setYear(mDate.getYear()+1900-1);
        mDate.setMonth(mDate.getMonth()+1);
        String date=mDate.getYear()+"-"+mDate.getMonth()+"-"+mDate.getDate();
        HttpSession session= request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) return userActivityReposotory.getAllActByUsername(username,date);
        }
        else return temp;
        return temp;
    }

    @RequestMapping(value = "/setAct")
    private String setAct(@RequestParam("username") String username,
                          @RequestParam("date") String date) {
        vcuser_activity_pk id=new vcuser_activity_pk(username,date);
        List<vcuser_activity> vcuserActivityList=userActivityReposotory.findActById(id);
        if(vcuserActivityList.isEmpty()) {
            vcuser_activity a=new vcuser_activity(id,1);
            userActivityReposotory.save(a);
            return "success";
        }
        vcuser_activity a=vcuserActivityList.get(0);
        if(a.getTimes()<12) a.setTimes(a.getTimes()+1);
        userActivityReposotory.save(a);
        return "success";
    }

}
