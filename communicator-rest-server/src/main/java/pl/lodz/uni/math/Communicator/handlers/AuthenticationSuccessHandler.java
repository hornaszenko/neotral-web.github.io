package pl.lodz.uni.math.Communicator.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.lodz.uni.math.Communicator.constants.SecurityConstants;
import pl.lodz.uni.math.Communicator.utils.TokenHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    private TokenHelper tokenHelper;

    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {

        clearAuthenticationAttributes(request);

        final User user = (User) authentication.getPrincipal();
        final String jwt = tokenHelper.generateToken(user);
        final UserTokenState userTokenState = new UserTokenState(jwt, SecurityConstants.EXPIRES_IN);
        try {
            final String jwtResponse = objectMapper.writeValueAsString(userTokenState);
            response.setContentType("application/json");
            response.getWriter().write(jwtResponse);
        } catch (Exception e) {
            LOG.error("JSON Web Token write to http response failed.");
        }
    }

    @Autowired
    public void setTokenHelper(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Getter
    @Setter
    private class UserTokenState {
        private String jwt;

        private int expires;

        public UserTokenState(String jwt, int expires) {
            this.jwt = jwt;
            this.expires = expires;
        }
    }
}
