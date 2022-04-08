package project.io.ranker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.io.ranker.dto.RoleDTO;
import project.io.ranker.dto.payload.request.LoginRequest;
import project.io.ranker.dto.payload.request.SignupRequest;
import project.io.ranker.models.RoleModel;
import project.io.ranker.models.UserModel;
import project.io.ranker.service.RoleService;
import project.io.ranker.service.UserService;

import java.util.Collections;

@SpringBootApplication
public class RankerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RankerApplication.class, args);
	}

	@Bean
	CommandLineRunner createDefaultRole (RoleService roleService) {
		return args -> {
			roleService.create(new RoleDTO());
		};
	}

	@Bean
	CommandLineRunner createDefaultUser (UserService userService) {
		return args -> {
			userService.signup(new SignupRequest("admin", Collections.singleton("ROLE_ADMIN"), "12345678"));
		};
	}
}
