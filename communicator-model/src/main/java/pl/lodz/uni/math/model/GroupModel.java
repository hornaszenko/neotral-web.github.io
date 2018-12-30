package pl.lodz.uni.math.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Conversation group entity.
 *
 * @author Piotr Krzyminski
 */
@Entity
@Table(name = "groups")
@Getter
@Setter
@SequenceGenerator(name="group_seq", initialValue=100)
public class GroupModel {

    /**
     * Unique id key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    private Long id;

    /**
     * Unique conversation group name.
     */
    @Column(name = "name", unique = true, nullable = false)
    @NotBlank
    @Size(min = 4, max = 64)
    private String name;

    /**
     * Is group visible to all user's and everybody has access to it.
     */
    @Column(name = "is_private", nullable = false)
    private Boolean privateGroup;

    /**
     * Group profile image path.
     */
    @Column(name = "image")
    private String image;

    /**
     * Group creation time.
     */
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    /**
     * Messages send in conversation group.
     */
    @OneToMany(mappedBy = "group")
    private List<MessageModel> messages;

    /**
     * Many to many relation between conversation groups and users.
     */
    @ManyToMany(mappedBy = "groups")
    private Set<UserModel> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupModel that = (GroupModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
