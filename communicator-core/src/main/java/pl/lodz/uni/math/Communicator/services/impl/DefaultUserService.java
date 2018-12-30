package pl.lodz.uni.math.Communicator.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.uni.math.Communicator.dtos.GroupsDTO;
import pl.lodz.uni.math.Communicator.dtos.UserDTO;
import pl.lodz.uni.math.Communicator.exceptions.AmbiguousUserException;
import pl.lodz.uni.math.Communicator.exceptions.UserNotFoundException;
import pl.lodz.uni.math.Communicator.services.UserService;
import pl.lodz.uni.math.model.GroupModel;
import pl.lodz.uni.math.model.UserModel;

import javax.persistence.NoResultException;

/**
 * Default implementation of {@link UserService}
 *
 * @author Piotr Krzyminski
 */
@Service
public class DefaultUserService implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserService.class);

    private UserDTO userDTO;

    private GroupsDTO groupsDTO;

    @Override
    public void saveUser(UserModel user) throws AmbiguousUserException {
        try {
            userDTO.save(user);
        } catch (RuntimeException exception) {
            LOG.warn("Cannot save user with username {} because user with this name already exists", user.getUsername());
            throw new AmbiguousUserException();
        }
    }

    @Override
    public UserModel findUserByUsername(String username) throws UserNotFoundException {
        try {
            return userDTO.getByUsername(username);
        } catch (NoResultException e) {
            LOG.debug("No user found with username {}", username);
            throw new UserNotFoundException();
        }
    }

    @Override
    public GroupModel addGroup(String username, GroupModel groupModel) throws UserNotFoundException {
        final UserModel user = userDTO.getByUsername(username);
        if(user != null) {
            user.getGroups().add(groupModel);
            userDTO.save(user);
            return groupsDTO.findById(groupModel.getId());
        } else {
            LOG.error("Cannot add group to user that not exists");
            throw new UserNotFoundException();
        }
    }

    @Autowired
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Autowired
    public void setGroupsDTO(GroupsDTO groupsDTO) {
        this.groupsDTO = groupsDTO;
    }
}
