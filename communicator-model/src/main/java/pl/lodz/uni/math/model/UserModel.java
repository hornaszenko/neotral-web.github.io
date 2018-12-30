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
 * User entity.
 *
 * @author Piotr Krzyminski
 */
@Entity
@Table(name = "persons")
@Getter
@Setter
@SequenceGenerator(name="user_seq", initialValue=100)
public class UserModel {

    /**
     * Unique id key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    /**
     * Unique user name used for authentication.
     */
    @Column(name = "username", unique = true, nullable = false)
    @NotBlank
    @Size(min = 6, max = 20)
    private String username;

    /**
     * User's password user for authentication.
     */
    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    /**
     * User's first name.
     */
    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 64)
    private String firstName;

    /**
     * User's surname.
     */
    @Column(name = "surname", nullable = false)
    @NotBlank
    @Size(min = 2, max = 64)
    private String surname;

    /**
     * User's role for example 'Admin'
     */
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleModel role;

    /**
     * Path to profile image file.
     */
    @Column(name = "image")
    private String image;

    /**
     * Time when user created account.
     */
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    /**
     * List of messages created by user.
     */
    @OneToMany(mappedBy = "author")
    private List<MessageModel> sentMessages;

    /**
     * List of message whose user receiver.
     */
    @OneToMany(mappedBy = "receiver")
    private List<MessageModel> receivedMessages;

    /**
     * Many to many relation between conversation groups and users.
     */
    @ManyToMany
    @JoinTable(name = "users_groups", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private Set<GroupModel> groups;

    /**
     * User user logged in now.
     */
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) &&
                Objects.equals(username, userModel.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
