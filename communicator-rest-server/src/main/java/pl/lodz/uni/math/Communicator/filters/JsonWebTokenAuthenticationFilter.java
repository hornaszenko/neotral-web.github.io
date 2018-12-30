package pl.lodz.uni.math.Communicator.filters;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.lodz.uni.math.Communicator.constants.SecurityConstants;
import pl.lodz.uni.math.Communicator.utils.TokenHelper;
import pl.lodz.uni.math.model.TokenBasedAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JSON Web Token authentication filter. Fired up every request.
 *
 * @author Piotr Krzyminski.
 */
public class JsonWebTokenAuthenticationFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;

    private TokenHelper tokenHelper;

    public JsonWebTokenAuthenticationFilter(UserDetailsService userDetailsService, TokenHelper tokenHelper) {
        this.userDetailsService = userDetailsService;
        this.tokenHelper = tokenHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authToken = getToken(request);
        if (authToken != null) {
            final String username = tokenHelper.getUsernameForToken(authToken);
            if (username != null) {
                final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                authentication.setToken(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                SecurityContextHolder.getContext().getAuthentication().setAuthenticated(true);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Checks if JSON Web Token is exists in http request header and extract it.
     *
     * @param request http request.
     * @return JSON Web Token.
     */
    private String getToken(final HttpServletRequest request) {
        final String authHeader = request.getHeader(SecurityConstants.AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
}
