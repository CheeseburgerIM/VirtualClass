package com.example.vclassserver.controller;

import com.example.vclassserver.entity.vclassuser;
import com.example.vclassserver.entity.vcuser_info;
import com.example.vclassserver.repository.UserInfoRepository;
import com.example.vclassserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.vclassserver.session.SessionController.sessionMap;

@RestController
@RequestMapping(value = "/user/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/get/all")
    private List<vclassuser> getAllUser() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/add")
    private String addUser(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("status") String status,
                           HttpServletRequest request) {
        if(!userRepository.findUserByUsername(username).isEmpty()) return "fail";
        vclassuser user=new vclassuser(username,password,status);
        userRepository.save(user);
        vcuser_info info=new vcuser_info(username,status,username);
        userInfoRepository.save(info);
        HttpSession session=request.getSession();
        session.setAttribute(username,password);
        sessionMap.put(session.getId(),session);
        return session.getId();
    }

    @RequestMapping(value = "/update/username={username}/oldpwd={oldpwd}/newpwd={newpwd}")
    private String updateSingleTest(@PathVariable("username") String username,
                                    @PathVariable("oldpwd") String oldpwd,
                                    @PathVariable("newpwd") String newpwd) {
        if(!userRepository.findUserByUsername(username).isEmpty()) {
            vclassuser u = userRepository.findUserByUsername(username).get(0);
            if(u.getPassword().equals(oldpwd)) {
                u.setPassword(newpwd);
                userRepository.save(u);
                return "success";
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/login")
    private Object login(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpServletRequest request) {
        if(userRepository.findUserByUsername(username).isEmpty()) {
            Map<String,Object> temp=new HashMap<>();
            temp.put("status","fail");
            return temp;
        }
        vclassuser user=userRepository.findUserByUsername(username).get(0);
        String rightPsword=user.getPassword();
        if(rightPsword.equals(password)) {
            HttpSession session=request.getSession();
            sessionMap.put(session.getId(),session);
            session.setAttribute(username,password);
            Map<String,Object> temp=new HashMap<>();
            temp.put("sessionId",session.getId());
            temp.put("status",user.getStatus());
            return temp;
        }
        Map<String,Object> temp=new HashMap<>();
        temp.put("status","fail");
        return temp;
    }

    @RequestMapping(value = "/logout")
    private String logout(HttpServletRequest request,
                          @RequestParam("username") String username) {
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            if(session.getAttribute(username)!=null) {
                session.removeAttribute(username);
                return "success";
            }
            return "fail";
        }
        return "fail";
    }

    @RequestMapping(value = "/deleteAll")
    private String deleteAll() {
        userRepository.deleteAll();
        return "success";
    }

}
