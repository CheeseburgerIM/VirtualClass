package com.example.vclassserver.session;

//import com.sun.star.animations.Event;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Iterator;

import static com.example.vclassserver.session.SessionController.sessionMap;

@Component
public class SessionRefresher {

    @Scheduled(cron = "0 0 5 * * ?")
    private void refreshSession() {
        // 每天凌晨5点刷新sessionMap
        Iterator<HttpSession> iterator=sessionMap.values().iterator();
        while(iterator.hasNext()) {
            iterator.next().invalidate();
        }
        sessionMap.clear();
        System.out.println(new Date()+"Clear sessionMap");
    }

//    @Scheduled(cron = "0 30 * ? * *")
//    private void logInfo() {
//        System.out.println(new Date());
//    }

}
