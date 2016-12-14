package com.e2open.common.util;

import com.trawells.common.FeatureConstants;
import com.trawells.common.exception.DataBaseException;
import com.trawells.dao.UserDAO;
import com.trawells.dao.entity.User;
import com.trawells.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;

/**
 * Created by abhishek on 9/22/16.
 */

@Component
public class RESTResourceBase {

    public static Logger logger = Logger.getLogger(RESTResourceBase.class.getCanonicalName());

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    public User loggedInUser;


    public boolean checkUser(HttpServletRequest request, HttpHeaders headers){
        if (FeatureConstants.allowSessionAuthorization && request != null) {

            String userId = getLoggedInId(request);

            try {
                loggedInUser = userDAO.getUserById(userId);
            } catch (DataBaseException e) {
                logger.error("error while fetching user : ", e);
            }

            if (loggedInUser != null)
                return Boolean.TRUE;
            else
                return Boolean.FALSE;
        }
        return Boolean.FALSE;
    }

    public String getLoggedInId(HttpServletRequest request) {
        HttpSession session = userService.getSession(request);

        logger.info("User Id from existing session = " + session.getAttribute("USER_ID"));

        String userId = null;
        if (session.getAttribute("USER_ID") != null) {
            userId = (String) session.getAttribute("USER_ID");
            return userId;
        }

        Cookie[] cookies = request.getCookies();
        logger.info("Getting cookies");
        if (cookies != null) {
            logger.info("Getting cookies size " + cookies.length);
            for (Cookie cookie : cookies){
                logger.info("Cookie name " + cookie.getName() + ", value " + cookie.getValue());
                if ("token.t".equals(cookie.getName())){
                    if ( cookie.getValue() == null)
                        continue;
                    String[] token = cookie.getValue().split(".");
                    User user = null;
                    try {
                        user =userDAO.getUserByIdToken(token[0], token[1]);
                    } catch (DataBaseException e) {
                        logger.error("RestResource base user token dao query fail : ",e);
                    }
                    if (user != null)
                        return user.getId();
                }
            }
        }
        logger.info("Finished procesing cookies");
        return null;
    }
}
