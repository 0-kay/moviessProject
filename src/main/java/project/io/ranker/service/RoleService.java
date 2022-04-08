package project.io.ranker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.io.ranker.Repositories.RoleRepo;
import project.io.ranker.dto.RoleDTO;
import project.io.ranker.models.RoleModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepository;

    public List<RoleDTO> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(role -> mapToDTO(role, new RoleDTO()))
                .collect(Collectors.toList());
    }

    public Long create(RoleDTO roleDTO) {
        if (roleRepository.existsByName(roleDTO.getName())) {
            return 413l;
        }

        final RoleModel role = new RoleModel();
        mapToEntity(roleDTO, role);
        return roleRepository.save(role).getId();
    }

    public void update(Long id, RoleDTO roleDTO) {
        final RoleModel role = roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(roleDTO, role);
        roleRepository.save(role);
    }

    public void delete(final Long id) {
        roleRepository.deleteById(id);
    }

    private RoleDTO mapToDTO(RoleModel role,
                             RoleDTO roleDTO) {
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    private RoleModel mapToEntity(RoleDTO roleDTO,
                                     RoleModel role) {
        role.setName(roleDTO.getName());
        return role;
    }
}
