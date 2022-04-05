package project.io.ranker.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.io.ranker.Repositories.RoleRepo;
import project.io.ranker.Repositories.UserModelRepo;
import project.io.ranker.dto.payload.request.LoginRequest;
import project.io.ranker.dto.payload.request.SignupRequest;
import project.io.ranker.dto.payload.response.MessageResponse;
import project.io.ranker.dto.payload.response.UserInfoResponse;
import project.io.ranker.models.EnumRole;
import project.io.ranker.models.RoleModel;
import project.io.ranker.models.UserDetailsImpl;
import project.io.ranker.models.UserModel;
import project.io.ranker.security.jwt.JwtUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserModelRepo userModelRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    public ResponseEntity<?> signin(LoginRequest loginRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        if(!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Invalid Username/Password");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtUtils.generateTokenFromUsername(userDetails))
                .body(new UserInfoResponse(
                        userDetails.getId(),
                        userDetails.getUsername(),
                        roles
                ));
    }

    public ResponseEntity<?> signup(SignupRequest signupRequest) {
        if (userModelRepo.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        // create the user account
        UserModel user = new UserModel(signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()));
        Set<String> strRoles = signupRequest.getRole();
        Set<RoleModel> roles = new HashSet<>();

        if (strRoles == null) {
            RoleModel adminRole = roleRepo.findByName(EnumRole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "user":
                        RoleModel userRole = roleRepo.findByName(EnumRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        break;
                    default:
                        RoleModel adminRole = roleRepo.findByName(EnumRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                }
            });
        }
        user.setRoles(roles);
        userModelRepo.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }
}
