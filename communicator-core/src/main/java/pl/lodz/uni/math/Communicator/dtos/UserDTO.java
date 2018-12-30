package pl.lodz.uni.math.Communicator.dtos;

import pl.lodz.uni.math.model.GroupModel;
import pl.lodz.uni.math.model.UserModel;

import javax.persistence.NoResultException;

/**
 * Data access object for {@link pl.lodz.uni.math.model.UserModel}
 *
 * @author Piotr Krzyminski
 */
public interface UserDTO {

    /**
     * Saves user to datasource.
     */
    void save(final UserModel user) throws RuntimeException;

    /**
     * Search for user by unique username.
     *
     * @param username username.
     * @return user with specified username or null if not found.
     * @throws NoResultException no user found.
     */
    UserModel getByUsername(final String username) throws NoResultException;

    void saveGroupForUser(final GroupModel groupModel);
}
