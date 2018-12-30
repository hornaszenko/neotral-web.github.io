package pl.lodz.uni.math.Communicator.facades.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.lodz.uni.math.Communicator.data.GroupData;
import pl.lodz.uni.math.Communicator.data.UserData;
import pl.lodz.uni.math.Communicator.exceptions.AmbiguousUserException;
import pl.lodz.uni.math.Communicator.exceptions.UserNotFoundException;
import pl.lodz.uni.math.Communicator.exceptions.UserRegisterException;
import pl.lodz.uni.math.Communicator.services.UserService;
import pl.lodz.uni.math.Communicator.facades.UserFacade;
import pl.lodz.uni.math.model.GroupModel;
import pl.lodz.uni.math.model.UserModel;

import java.util.Date;

/**
 * Default implementation of {@link pl.lodz.uni.math.Communicator.facades.UserFacade}
 *
 * @author Piotr Krzyminski
 */
@Component("userFacade")
public class DefaultUserFacade implements UserFacade {

    private UserService userService;

    private Converter<UserModel, UserData> userDataConverter;

    private Converter<GroupModel, GroupData> groupDataConverter;

    @Override
    public void register(UserData user) throws UserRegisterException {
        UserModel userModel = new UserModel();
        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        userModel.setFirstName(user.getFirstName());
        userModel.setSurname(user.getSurname());
        userModel.setCreationDate(new Date());

        try {
            userService.saveUser(userModel);
        } catch (AmbiguousUserException e) {
            throw new UserRegisterException();
        }
    }

    @Override
    public GroupData addGroupForUser(String username, GroupData groupData) throws UserNotFoundException {
        GroupModel groupModel = new GroupModel();
        groupModel.setId(groupData.getId());
        GroupModel result = userService.addGroup(username, groupModel);
        return groupDataConverter.convert(result);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserDataConverter(Converter<UserModel, UserData> userDataConverter) {
        this.userDataConverter = userDataConverter;
    }

    @Autowired
    public void setGroupDataConverter(Converter<GroupModel, GroupData> groupDataConverter) {
        this.groupDataConverter = groupDataConverter;
    }
}
