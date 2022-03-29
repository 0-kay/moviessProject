package project.io.ranker.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserModel {

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @ManyToMany( fetch = FetchType.LAZY )
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<RoleModel> roles = new HashSet<>();

}
