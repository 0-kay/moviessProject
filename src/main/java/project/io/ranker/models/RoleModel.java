package project.io.ranker.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;



@Data
@Entity
@ToString
@RequiredArgsConstructor
public class  RoleModel  {
    @Id
    @GeneratedValue( strategy = IDENTITY)
    private Long id;

    @Enumerated
    private EnumRole name;
}
