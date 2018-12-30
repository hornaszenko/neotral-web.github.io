package pl.lodz.uni.math.Communicator.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import pl.lodz.uni.math.Communicator.dtos.GroupsDTO;
import pl.lodz.uni.math.Communicator.dtos.UserDTO;
import pl.lodz.uni.math.Communicator.services.GroupsService;
import pl.lodz.uni.math.model.GroupModel;
import pl.lodz.uni.math.model.MessageModel;
import pl.lodz.uni.math.model.UserModel;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DefaultGroupsService implements GroupsService {

    private GroupsDTO groupsDTO;

    private UserDTO userDTO;

    @Override
    public Set<GroupModel> getGroupsForUserWithUsername(String username) {
        Set<GroupModel> groups;
        try {
            groups = groupsDTO.findByUsername(username);
        } catch (NoResultException e) {
            groups = new HashSet<>();
        }

        return groups;
    }

    @Override
    public Set<GroupModel> getNewGroupsForUser(String username) {
        Set<GroupModel> userGroups = getGroupsForUserWithUsername(username);
        Set<GroupModel> allGroups = groupsDTO.findAll();
        Set<GroupModel> result = new HashSet<>();
        for(GroupModel group : allGroups) {
            if(!userGroups.contains(group)) {
                result.add(group);
            }
        }

        return result;
    }

    @Override
    public List<MessageModel> getAllMessages(Long id) {
        try {
            final GroupModel group = groupsDTO.findById(id);
            return groupsDTO.getMessagesForGroup(group);
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public GroupModel getGroupById(long id) {
        try {
            final GroupModel group = groupsDTO.findById(id);
            return group;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void removeUserFromGroup(GroupModel group, String username) {
        try {
            UserModel user = userDTO.getByUsername(username);
            GroupModel groupModel = groupsDTO.findById(group.getId());
            user.getGroups().remove(groupModel);
            groupModel.getUsers().remove(user);

            groupsDTO.removeUserFromGroup(groupModel, user);

        } catch (NoResultException e) { }
    }

    @Override
    public GroupModel addNewGroup(GroupModel groupModel) {
        return groupsDTO.add(groupModel);
    }

    @Override
    public MessageModel saveMessage(MessageModel message) {
        return groupsDTO.addMessage(message);
    }

    @Autowired
    public void setGroupsDTO(GroupsDTO groupsDTO) {
        this.groupsDTO = groupsDTO;
    }

    @Autowired
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
