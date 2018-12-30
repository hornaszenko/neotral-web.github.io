package pl.lodz.uni.math.Communicator.dtos;

import pl.lodz.uni.math.model.GroupModel;
import pl.lodz.uni.math.model.MessageModel;
import pl.lodz.uni.math.model.UserModel;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;

/**
 * Interface for {@link pl.lodz.uni.math.model.GroupModel}.
 *
 * @author Piotr Krzyminski
 */
public interface GroupsDTO {

    /**
     * Finds all conversation groups which user with specified username belongs.
     *
     * @param username username.
     * @return list of all groups where user is a member.
     * @throws NoResultException no result found.
     */
    Set<GroupModel> findByUsername(final String username) throws NoResultException;

    /**
     * Finds all conversation groups.
     *
     * @return all available conversation groups.
     */
    Set<GroupModel> findAll();

    List<MessageModel> getMessagesForGroup(final GroupModel groupModel);

    GroupModel findById(final Long id) throws NoResultException;

    MessageModel addMessage(final MessageModel message);

    void removeUserFromGroup(final GroupModel group, final UserModel user);

    GroupModel add(GroupModel group);
}
