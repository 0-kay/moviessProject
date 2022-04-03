package project.io.ranker.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserModel{

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
