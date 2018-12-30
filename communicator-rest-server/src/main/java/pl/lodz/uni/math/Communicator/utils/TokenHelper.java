package pl.lodz.uni.math.Communicator.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import pl.lodz.uni.math.Communicator.constants.SecurityConstants;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * Class used for parsing nad building JSON Web Token.
 *
 * @author Piotr Krzyminski.
 */
@Component
public class TokenHelper {

    /**
     * Extracts username value from JSON Web Token.
     *
     * @param token JSON Web Token.
     * @return username value.
     */
    public String getUsernameForToken(final String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            return null;
        }
    }

    /**
     * Generates JSON Web Token.
     *
     * @param user user for whose token will be generated.
     * @return generated JSON Web Token.
     */
    public String generateToken(final User user) {
        return "Bearer " + Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", user.getAuthorities().stream().
                        map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRES_IN))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
                .compact();
    }
}
