package com.scm.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

   public static void removeMessage(){

        try{
            System.out.println("Removing message from session");
            HttpSession session =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        //This will remove the message from the session
        //this works as a cleanup operation
        //Explaination of working:
        //1. We get the current session using RequestContextHolder
        //2. We then remove the message attribute from the session
        session.removeAttribute("message");

        } catch (Exception e) {
            System.out.println("Error in SessionHelper" + e);
            e.printStackTrace();
        
        //
    }
}
}
