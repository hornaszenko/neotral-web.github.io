package pl.lodz.uni.math.Communicator.services;

import pl.lodz.uni.math.Communicator.exceptions.AmbiguousUserException;
import pl.lodz.uni.math.Communicator.exceptions.UserNotFoundException;
import pl.lodz.uni.math.model.GroupModel;
import pl.lodz.uni.math.model.UserModel;

/**
 * Interface for {@link UserModel}.
 *
 * @author Piotr Krzyminski
 */
public interface UserService {

    /**
     * Saves new user to datasource.
     *
     * @param user user data.
     * @throws AmbiguousUserException user with selected username already exists. Username parameter must ne unique.
     */
    void saveUser(final UserModel user) throws AmbiguousUserException;

    /**
     * Find user by unique username parameter.
     *
     * @param username username.
     * @return user with specified username.
     * @throws UserNotFoundException user with specified username not exists.
     */
    UserModel findUserByUsername(final String username) throws UserNotFoundException;

    GroupModel addGroup(final String username, final GroupModel groupModel) throws UserNotFoundException;
}
