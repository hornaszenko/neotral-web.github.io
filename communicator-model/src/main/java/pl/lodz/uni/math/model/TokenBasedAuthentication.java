package pl.lodz.uni.math.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Model used for JSON Web Token authentication.
 *
 * @author Piotr Krzyminski.
 */
public class TokenBasedAuthentication extends AbstractAuthenticationToken {

    @Setter
    @Getter
    private String token;

    private UserDetails userDetails;

    public TokenBasedAuthentication(UserDetails userDetails) {
        super(userDetails.getAuthorities());
        this.userDetails = userDetails;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }
}
