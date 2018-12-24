package pl.lodz.uni.math.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Message entity.
 *
 * @author Piotr Krzyminski
 */
@Entity
@Table(name = "messages")
@Getter
@Setter
public class MessageModel {

    /**
     * Unique id key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    /**
     * Author of message.
     */
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserModel author;

    /**
     * Group where message was send. If null then message is private.
     */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupModel group;

    /**
     * Receiver of message. If it is null when message was sent to all members of group.
     */
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserModel receiver;

    /**
     * Message creation time.
     */
    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    /**
     * Text content of message.
     */
    @Column(name = "content", nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String content;
}
