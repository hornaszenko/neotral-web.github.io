package pl.lodz.uni.math.Communicator.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.lodz.uni.math.Communicator.data.UserData;
import pl.lodz.uni.math.model.UserModel;

import java.util.Date;

/**
 * Converter from {@link UserData} to {@link UserModel}.
 *
 * @author Piotr Krzyminski.
 */
@Component
public class UserDataConverter implements Converter<UserModel, UserData> {

    @Override
    public UserData convert(UserModel userModel) {
        UserData userData = new UserData();
        userModel.setFirstName(userData.getFirstName());
        userModel.setSurname(userData.getSurname());
        userModel.setUsername(userData.getUsername());
        userModel.setPassword(userData.getPassword());
        userModel.setCreationDate(new Date());

        return userData;
    }
}
