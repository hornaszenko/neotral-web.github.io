package pl.lodz.uni.math.Communicator.dtos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pl.lodz.uni.math.Communicator.dtos.UserDTO;
import pl.lodz.uni.math.model.GroupModel;
import pl.lodz.uni.math.model.UserModel;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Default implementation of {@link pl.lodz.uni.math.Communicator.dtos.UserDTO}
 *
 * @author Piotr Krzyminski
 */
@Repository
@Transactional
public class DefaultUserDTO implements UserDTO {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserDTO.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(UserModel user) throws RuntimeException {
        entityManager.persist(user);
    }

    @Override
    public UserModel getByUsername(String username) throws NoResultException {
        final String query = "SELECT u FROM UserModel u WHERE u.username = :name";
        final UserModel user = (UserModel) entityManager.createQuery(query).setParameter("name", username).getSingleResult();
        if(user == null) {
            LOG.warn("No user found with name {}", username);
        }
        return user;
    }

    @Override
    public void saveGroupForUser(GroupModel groupModel) {
        entityManager.persist(groupModel);
    }
}
