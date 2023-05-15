package com.domain.helpers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserAuthorize  {
    
    @Autowired
	private HttpServletRequest request;

    @Value("${app-auth}")
    private String KeyValidator;

    public boolean getAuthAccess(){
        boolean status;
        String key;
        try {
            key = request.getHeader("X-API-KEY").toString();
            if(key.equals(KeyValidator)){
                status = true;
            } else {
                status = false;
            }
        } catch (Exception e) {
            e.getStackTrace();
            status = false;
        }
        
        return status;
    }
}
