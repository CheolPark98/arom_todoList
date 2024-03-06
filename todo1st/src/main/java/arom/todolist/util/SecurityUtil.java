package arom.todolist.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Security;
import java.util.Optional;

public class SecurityUtil {
    private SecurityUtil(){}

    public static Long getCurrentMemberId(){
        final Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication ==null || authentication.getName() == null){
            throw new RuntimeException("Security Context에 인증정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }

    public static Optional<String> getCurrentEmail(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null){
            return Optional.empty();
        }
        System.out.println("authentication.getPrincipal() = " + authentication.getPrincipal());
        String username= null;
        if (authentication.getPrincipal() instanceof UserDetails){
             UserDetails member = (UserDetails) authentication.getPrincipal();
             username=member.getUsername();
        }else if (authentication.getPrincipal() instanceof  String){
            username = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(username);
    }
}
