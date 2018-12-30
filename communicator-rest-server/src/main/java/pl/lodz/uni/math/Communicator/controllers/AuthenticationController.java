package pl.lodz.uni.math.Communicator.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.uni.math.Communicator.data.UserData;
import pl.lodz.uni.math.Communicator.exceptions.UserRegisterException;
import pl.lodz.uni.math.Communicator.facades.UserFacade;

/**
 * Authentication controller.
 *
 * @author Piotr Krzyminski
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    private UserFacade userFacade;

    private PasswordEncoder passwordEncoder;

    /**
     * Get json data with user credentials, parse to object and try to register user.
     * Returns 200 status when registration will finish with success or 400 if not.
     *
     * @param user user to register.
     */
    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUp(@RequestBody UserData user) {
        LOG.info("Fired sign up");

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userFacade.register(user);
        } catch (UserRegisterException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
