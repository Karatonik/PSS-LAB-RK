package pss.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pss.demo.enums.ERole;
import pss.demo.models.Role;
import pss.demo.models.User;
import pss.demo.models.payload.request.LoginRequest;
import pss.demo.models.payload.request.SignupRequest;
import pss.demo.models.payload.response.JwtResponse;
import pss.demo.models.payload.response.MessageResponse;
import pss.demo.repositorys.RoleRepository;
import pss.demo.repositorys.UserRepository;
import pss.demo.jwt.JwtUtils;
import pss.demo.models.UserDetailsImpl;
import pss.demo.services.AuthServiceImp;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 7200)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthServiceImp authenticate;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return  authenticate.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return  authenticate.registerUser(signUpRequest);
	}
	@PostMapping("/fb")
	public ResponseEntity<?> signInByFb(SignupRequest signUpRequest){
		return authenticate.singInByFacebook(signUpRequest);
	}
	@PostMapping("/google")
	public ResponseEntity<?> signInByGoogle(SignupRequest signUpRequest){
		return authenticate.signInByGoogle(signUpRequest);
	}
}
