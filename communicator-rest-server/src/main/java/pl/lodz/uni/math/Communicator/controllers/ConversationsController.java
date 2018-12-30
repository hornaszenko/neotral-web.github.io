package pl.lodz.uni.math.Communicator.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.uni.math.Communicator.data.GroupData;
import pl.lodz.uni.math.Communicator.data.MessageData;
import pl.lodz.uni.math.Communicator.exceptions.SendMessageException;
import pl.lodz.uni.math.Communicator.exceptions.UserNotFoundException;
import pl.lodz.uni.math.Communicator.facades.GroupsFacade;
import pl.lodz.uni.math.Communicator.facades.UserFacade;
import pl.lodz.uni.math.Communicator.utils.SecurityUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConversationsController {

    private static final Logger LOG = LoggerFactory.getLogger(ConversationsController.class);

    private GroupsFacade groupsFacade;

    private UserFacade userFacade;

    /**
     * Get all available conversations for logged in user.
     *
     * @return all conversations for user.
     */
    @GetMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllGroups() {
        final String username = SecurityUtils.getUsernameFromContext();
        if (username == null) {
            LOG.warn("Unauthorized user tries to get conversations data");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        final List<GroupData> groups = groupsFacade.getAllGroupsForUser(username);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    /**
     * Get all new groups where user is not a member.
     *
     * @return all new conversations for user.
     */
    @GetMapping(value = "/groups/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllNewGroups() {
        final String username = SecurityUtils.getUsernameFromContext();
        if (username == null) {
            LOG.warn("Unauthorized user tries to get conversations data");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        final List<GroupData> groups = groupsFacade.getAllNewGroupsForUser(username);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PostMapping(value = "/groups/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addGroupForUser(@RequestBody GroupData groupData) {
        final String username = SecurityUtils.getUsernameFromContext();
        if (username == null) {
            LOG.warn("Unauthorized user tries to get conversations data");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            final GroupData result = userFacade.addGroupForUser(username, groupData);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping(params = {"id", "size"}, value = "/groups/messages")
    public ResponseEntity<?> getMessages(@RequestParam("id") long id, @RequestParam("size") int size) {
        final String username = SecurityUtils.getUsernameFromContext();
        if (username == null) {
            LOG.warn("Unauthorized user tries to get conversations data");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<MessageData> messages = groupsFacade.getMessages(id, size);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping(value = "/groups/{id}/messages/add")
    public ResponseEntity<?> saveMessage(@PathVariable long id, @RequestBody String message) {
        final String username = SecurityUtils.getUsernameFromContext();
        if (username == null) {
            LOG.warn("Unauthorized user tries to get conversations data");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            MessageData result = groupsFacade.saveGroupMessage(message, id, username);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (SendMessageException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/groups/remove/{id}")
    public ResponseEntity<?> removeUserFromGroup(@PathVariable("id") int conversationId) {
        final String username = SecurityUtils.getUsernameFromContext();
        if (username == null) {
            LOG.warn("Unauthorized user tries to get conversations data");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        groupsFacade.removeUserFromGroup(conversationId, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/groups/add")
    public ResponseEntity<?> addNewGroup(@RequestBody GroupData group) {
        if(group == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        final String username = SecurityUtils.getUsernameFromContext();
        if (username == null) {
            LOG.warn("Unauthorized user tries to get conversations data");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        GroupData result = groupsFacade.addGroup(group);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Autowired
    public void setGroupsFacade(GroupsFacade groupsFacade) {
        this.groupsFacade = groupsFacade;
    }

    @Autowired
    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }
}
