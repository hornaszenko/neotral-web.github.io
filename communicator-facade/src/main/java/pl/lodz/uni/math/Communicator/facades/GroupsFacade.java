package pl.lodz.uni.math.Communicator.facades;

import pl.lodz.uni.math.Communicator.data.GroupData;
import pl.lodz.uni.math.Communicator.data.MessageData;
import pl.lodz.uni.math.Communicator.exceptions.SendMessageException;

import java.util.List;

/**
 * Interface for {@link GroupData}.
 *
 * @author Piotr Krzyminski
 */
public interface GroupsFacade {

    /**
     * Get all groups for user.
     *
     * @param username username.
     * @return all groups where user is a member.
     */
    List<GroupData> getAllGroupsForUser(final String username);

    /**
     * Get list of all conversation group.
     *
     * @param username username.
     * @return all available groups.
     */
    List<GroupData> getAllNewGroupsForUser(String username);

    /**
     * Get list of all conversation group.
     *
     * @param username username.
     * @param size number of results.
     * @return all available groups.
     */
    List<GroupData> getAllNewGroupsForUser(String username, int size);

    List<MessageData> getAllMessages(final Long id);

    List<MessageData> getMessages(final Long id, final int size);

    MessageData saveGroupMessage(final String message, final long id, final String username) throws SendMessageException;

    void removeUserFromGroup(final long conversationId, final String username);

    GroupData addGroup(final GroupData groupData);
}
