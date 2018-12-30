package pl.lodz.uni.math.Communicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lodz.uni.math.Communicator.exceptions.UserNotFoundException;
import pl.lodz.uni.math.Communicator.services.UserService;
import pl.lodz.uni.math.model.UserModel;

import java.util.Collections;

/**
 * Default implementation of {@link UserDetailsService}. Used for authentication.
 *
 * @author Piotr Krzyminski
 */
@Service("userDetailsService")
public class DefaultUserDetailsService implements UserDetailsService {

    private UserService userService;

    /**
     * Get user from database by username and check credentials.
     *
     * @param s username from login form.
     * @return authenticated user.
     * @throws UsernameNotFoundException user with the following username not exists in database.
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            UserModel user = userService.findUserByUsername(s);

            return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
