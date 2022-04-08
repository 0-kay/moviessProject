package project.io.ranker.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class  RoleModel  {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Long id;

    @Enumerated(STRING)
    private EnumRole name;
}
