package project.io.ranker.dto;

import lombok.Getter;
import lombok.Setter;
import project.io.ranker.models.EnumRole;

@Getter
@Setter
public class RoleDTO {
    private Long id;

    private EnumRole name = EnumRole.ROLE_ADMIN;
}
