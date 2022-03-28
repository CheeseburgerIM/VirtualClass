package com.example.vclassserver.session;

import com.example.vclassserver.entity.vclassuser;
import com.example.vclassserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/session")
public class SessionController {

//    @Resource
//    HttpSession session; // test1
//
//    @GetMapping(value = "/setSession/test1")
//    private Object setSessionTest1(@RequestParam("username") String username,
//                              @RequestParam("login") int login) {
//        if(login==1) {
//            session.setAttribute(username,login);
//            return "login";
//        }
//        else if(login==0) {
//            session.removeAttribute(username);
//            return "logout";
//        }
//        return null;
//    }
//
//    @GetMapping(value = "/getSession/test1")
//    private Object getSessionTest1(@RequestParam("username") String username) {
//        if(session.getAttribute(username)==null) {
//            return "fail";
//        }
//        else {
//            return "success";
//        }
//    }
//
//    @RequestMapping(value = "/setSession/test2/username={username}/password={password}")
//    private Object setSessionTest2(HttpServletRequest request,
//                                   @PathVariable("username") String username,
//                                   @PathVariable("password") String password) {
//        HttpSession session=request.getSession();
//        session.setAttribute(username,password);
//        return "success";
//    }
//
//    @GetMapping(value = "/getSession/test2/username={username}")
//    private Object getSessionTest2(HttpServletRequest request,
//                                   @PathVariable("username") String username) {
//        HttpSession session=request.getSession();
//        if(session.getAttribute(username)==null) return "logout";
//        else return "login";
//    }

    @Autowired
    UserRepository userRepository;

    public static Map<String, HttpSession> sessionMap=new HashMap<>();

    @RequestMapping(value = "/setSession/username={username}/password={password}")
    private Object setSession(HttpServletRequest request,
                              @PathVariable("username") String username,
                              @PathVariable("password") String password) {
        HttpSession session=request.getSession();
        session.setAttribute(username,password);
        sessionMap.put(session.getId(),session);
        Map<String,Object> temp=new HashMap<>();
        temp.put("sessionId",session.getId());
        vclassuser user=userRepository.findUserByUsername(username).get(0);
        temp.put("status",user.getStatus());
//        temp.put("users",userRepository.findAll());
//        return session.getId();
        return temp;
    }

    @RequestMapping(value = "/getSession/sessionId={sessionId}/username={username}")
    private Object getSession(@PathVariable("sessionId") String sessionId,
                              @PathVariable("username") String username) {
        if(sessionMap.containsKey(sessionId)) {
            HttpSession session=sessionMap.get(sessionId);
            if(session.getAttribute(username)!=null) return "success";
        }
        else return "fail";
        return "fail";
    }

    @RequestMapping(value = "/getSession")
    private Object Session(HttpServletRequest request) {
        System.out.println("-------");
        HttpSession session=request.getSession();
        if(sessionMap.containsKey(session.getId())) {
            System.out.println("Contain: "+sessionMap.keySet());
            return session.getId()+" - "+request.getSession().getId();
        }
        System.out.println("Before: "+sessionMap.keySet());
        sessionMap.put(session.getId(),session);
        System.out.println("After: "+sessionMap.keySet());
        return session.getId()+" - "+request.getSession().getId();
    }

}
