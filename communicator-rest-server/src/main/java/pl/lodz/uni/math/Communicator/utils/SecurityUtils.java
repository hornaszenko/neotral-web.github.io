package pl.lodz.uni.math.Communicator.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    /**
     * Get username from security context.
     * @return logged in username.
     */
    public static String getUsernameFromContext() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null) {
            return null;
        }

        return auth.getName();
    }
}
