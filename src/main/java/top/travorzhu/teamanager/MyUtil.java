package top.travorzhu.teamanager;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUtil {
    public static String getUsername(Authentication auth){
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userDetails.getUsername();
    }

    public static boolean isLogin(Authentication auth){
        return !(auth instanceof AnonymousAuthenticationToken);
    }
}
