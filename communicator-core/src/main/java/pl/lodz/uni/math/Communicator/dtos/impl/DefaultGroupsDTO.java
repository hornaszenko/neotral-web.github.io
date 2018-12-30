package pl.lodz.uni.math.Communicator.dtos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pl.lodz.uni.math.Communicator.dtos.GroupsDTO;
import pl.lodz.uni.math.model.GroupModel;
import pl.lodz.uni.math.model.MessageModel;
import pl.lodz.uni.math.model.UserModel;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of {@link GroupsDTO}.
 *
 * @author Piotr Krzyminski
 */
@Repository
@Transactional
public class DefaultGroupsDTO implements GroupsDTO {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultGroupsDTO.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<GroupModel> findByUsername(String username) throws NoResultException {
        final String query = "SELECT u.groups FROM UserModel u WHERE u.username = :name";
        final List<GroupModel> resultList = entityManager.createQuery(query).setParameter("name", username).getResultList();
        if(resultList.isEmpty()) {
            LOG.debug("No groups found for user with name {}", username);
        }

        return new HashSet<>(resultList);
    }

    @Override
    public Set<GroupModel> findAll() {
        final String query = "SELECT g FROM GroupModel g ORDER BY g.name ASC";
        final List<GroupModel> resultList = entityManager.createQuery(query).getResultList();
        if(resultList.isEmpty()) {
            LOG.debug("No groups found");
        }

        return new HashSet<>(resultList);
    }

    @Override
    public List<MessageModel> getMessagesForGroup(GroupModel groupModel) {
        final String query = "SELECT m FROM MessageModel m WHERE m.group = :group";
        final List<MessageModel> resultList = entityManager.createQuery(query).setParameter("group", groupModel).getResultList();
        if(resultList.isEmpty()) {
            LOG.debug("Not messages found");
        }

        return resultList;
    }

    @Override
    public MessageModel addMessage(MessageModel message) {
        entityManager.persist(message);
        entityManager.flush();

        final String query = "SELECT m FROM MessageModel m WHERE m.id = :id";
        final MessageModel result = (MessageModel) entityManager.createQuery(query).setParameter("id", message.getId()).getSingleResult();
        return result;
    }

    @Override
    public GroupModel findById(Long id) throws NoResultException {
        final String query = "SELECT g FROM GroupModel g WHERE g.id = :id";
        return (GroupModel) entityManager.createQuery(query).setParameter("id", id).getSingleResult();
    }

    @Override
    public void removeUserFromGroup(GroupModel group, UserModel user) {
        entityManager.merge(user);
        entityManager.merge(group);
    }

    @Override
    public GroupModel add(GroupModel group) {
        entityManager.persist(group);
        entityManager.flush();

        return group;
    }
}
