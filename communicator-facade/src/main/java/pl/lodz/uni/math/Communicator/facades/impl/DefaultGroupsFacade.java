package pl.lodz.uni.math.Communicator.facades.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.lodz.uni.math.Communicator.data.GroupData;
import pl.lodz.uni.math.Communicator.data.MessageData;
import pl.lodz.uni.math.Communicator.exceptions.SendMessageException;
import pl.lodz.uni.math.Communicator.exceptions.UserNotFoundException;
import pl.lodz.uni.math.Communicator.facades.GroupsFacade;
import pl.lodz.uni.math.Communicator.services.GroupsService;
import pl.lodz.uni.math.Communicator.services.UserService;
import pl.lodz.uni.math.model.GroupModel;
import pl.lodz.uni.math.model.MessageModel;
import pl.lodz.uni.math.model.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Default implementation for {@link GroupsFacade}.
 *
 * @author Piotr Krzyminski
 */
@Component
public class DefaultGroupsFacade implements GroupsFacade {

    private GroupsService groupsService;

    private UserService userService;

    private Converter<GroupModel, GroupData> groupDataConverter;

    private Converter<MessageModel, MessageData> messageDataConverter;

    @Override
    public List<GroupData> getAllGroupsForUser(String username) {
        final Set<GroupModel> groupModelSet = groupsService.getGroupsForUserWithUsername(username);
        List<GroupData> groups = new ArrayList<>();
        for (GroupModel group : groupModelSet) {
            final GroupData groupData = groupDataConverter.convert(group);
            if (groupData != null) {
                groupData.setMember(true);
                groups.add(groupData);
            }
        }

        return groups;
    }

    @Override
    public List<GroupData> getAllNewGroupsForUser(String username) {
        final Set<GroupModel> groupModelSet = groupsService.getNewGroupsForUser(username);
        List<GroupData> groups = new ArrayList<>();
        for (GroupModel group : groupModelSet) {
            final GroupData groupData = groupDataConverter.convert(group);
            if (groupData != null) {
                groupData.setMember(false);
                groups.add(groupData);
            }
        }

        return groups;
    }

    @Override
    public List<MessageData> getAllMessages(Long id) {
        List<MessageData> result = new ArrayList<>();
        final List<MessageModel> messageModelList = groupsService.getAllMessages(id);
        for (MessageModel messageModel : messageModelList) {
            MessageData messageData = messageDataConverter.convert(messageModel);
            result.add(messageData);
        }

        return result;
    }

    @Override
    public List<GroupData> getAllNewGroupsForUser(String username, int size) {
        final List<GroupData> groups = getAllNewGroupsForUser(username);

        return groups.subList(0, size);
    }

    @Override
    public MessageData saveGroupMessage(String message, long groupId, String username) throws SendMessageException {
        try {
            UserModel user = userService.findUserByUsername(username);
            GroupModel group = groupsService.getGroupById(groupId);
            if (group != null) {
                MessageModel messageModel = new MessageModel();
                messageModel.setContent(message);
                messageModel.setCreationDate(new Date());
                messageModel.setAuthor(user);
                messageModel.setGroup(group);

                final MessageModel result = groupsService.saveMessage(messageModel);
                return messageDataConverter.convert(result);
            }
        } catch (UserNotFoundException e) {
            throw new SendMessageException();
        }

        throw new SendMessageException();
    }

    @Override
    public List<MessageData> getMessages(Long id, int size) {
        List<MessageData> allMessages = getAllMessages(id);
        if(allMessages.isEmpty()) {
            return allMessages;
        }

        int finalSize = allMessages.size() - size;
        if(finalSize < 0) {
            finalSize = 0;
        }

        return allMessages.subList(finalSize, allMessages.size());
    }

    @Override
    public void removeUserFromGroup(final long conversationId, String username) {
        GroupModel group = new GroupModel();
        group.setId(conversationId);

        groupsService.removeUserFromGroup(group, username);
    }

    @Override
    public GroupData addGroup(GroupData groupData) {
        GroupModel groupModel = new GroupModel();
        groupModel.setName(groupData.getName());
        groupModel.setCreationDate(new Date());
        groupModel.setPrivateGroup(false);

        return groupDataConverter.convert(groupsService.addNewGroup(groupModel));
    }

    @Autowired
    public void setGroupsService(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setGroupDataConverter(Converter<GroupModel, GroupData> groupDataConverter) {
        this.groupDataConverter = groupDataConverter;
    }

    @Autowired
    public void setMessageDataConverter(Converter<MessageModel, MessageData> messageDataConverter) {
        this.messageDataConverter = messageDataConverter;
    }
}
