package pl.lodz.uni.math.Communicator.services;

import pl.lodz.uni.math.model.GroupModel;
import pl.lodz.uni.math.model.MessageModel;

import java.util.List;
import java.util.Set;

/**
 * Service for {@link pl.lodz.uni.math.model.GroupModel}.
 *
 * @author Piotr Krzyminski
 */
public interface GroupsService {

    /**
     * Finds all conversation groups for user by his username.
     *
     * @param username username.
     * @return set of all conversation groups where user is a member.
     */
    Set<GroupModel> getGroupsForUserWithUsername(final String username);

    /**
     * Finds all conversation groups where user is not a member.
     *
     * @param username username.
     * @return groups where user is not a member.
     */
    Set<GroupModel> getNewGroupsForUser(final String username);

    List<MessageModel> getAllMessages(final Long id);

    MessageModel saveMessage(final MessageModel message);

    GroupModel getGroupById(final long id);

    void removeUserFromGroup(GroupModel group, final String username);

    GroupModel addNewGroup(GroupModel groupModel);
}
