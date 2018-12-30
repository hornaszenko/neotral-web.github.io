package pl.lodz.uni.math.Communicator.facades;

import pl.lodz.uni.math.Communicator.data.GroupData;
import pl.lodz.uni.math.Communicator.data.UserData;
import pl.lodz.uni.math.Communicator.exceptions.UserNotFoundException;
import pl.lodz.uni.math.Communicator.exceptions.UserRegisterException;

public interface UserFacade {

    void register(final UserData user) throws UserRegisterException;

    GroupData addGroupForUser(final String username, final GroupData groupData) throws UserNotFoundException;
}
