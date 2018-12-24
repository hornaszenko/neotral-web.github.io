package pl.lodz.uni.math.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Role entity.
 *
 * @author Piotr Krzyminski
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleModel {

    /**
     * Unique id key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    /**
     * Unique role name.
     */
    @Column(name = "name")
    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    /**
     * List of user's with assigned specific role.
     */
    @OneToMany(mappedBy = "role")
    private Set<UserModel> users;
}
