/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dto.UserDTO;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tamph
 */
public class AuthUtils {
    public static final String FOUNDER_ROLE = "Founder";
    public static final String MEMBER_ROLE = "Team Member";
    
    public static boolean isLoggedIn(HttpSession session){
        return session.getAttribute("user") != null;
    }
    
    public static boolean isAdmin(HttpSession session){
        if(!isLoggedIn(session))
            return false;
        UserDTO user = (UserDTO)session.getAttribute("user");
        return user.getRole().equals(FOUNDER_ROLE);
    }
    
    
            
}
