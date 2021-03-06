package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pss.demo.enums.ERole;
import pss.demo.jwt.JwtUtils;
import pss.demo.models.Role;
import pss.demo.models.User;
import pss.demo.models.UserDetailsImpl;
import pss.demo.models.payload.request.LoginRequest;
import pss.demo.models.payload.request.SignupRequest;
import pss.demo.models.payload.response.JwtResponse;
import pss.demo.models.payload.response.MessageResponse;
import pss.demo.repositorys.RoleRepository;
import pss.demo.repositorys.UserRepository;
import pss.demo.services.interfaces.AuthService;
import pss.demo.services.interfaces.EmailService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class AuthServiceImp implements AuthService {
    EmailService emailService;

    AuthenticationManager authenticationManager;

    UserRepository userRepository;

    RoleRepository roleRepository;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;


    @Autowired
    public AuthServiceImp(AuthenticationManager authenticationManager, UserRepository userRepository
            , RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.emailService=emailService;
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest ,String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        if(!password.equals("")){
         User user =  userRepository.findById(userDetails.getId()).get();
         user.setPassword(password);
         userRepository.save(user);
        }

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,userDetails.isStatus()));
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByName(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getCompanyName(),signUpRequest.getCompanyAddress(),
                signUpRequest.getCompanyNip(),signUpRequest.getName(),signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByRoleName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoleSet(roles);
    User dbUser= userRepository.save(user);
        emailService.sendConfirmation(dbUser.getUserId());
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @Override
    public ResponseEntity<?> singInByExternal(SignupRequest signUpRequest) {


      Optional<User>optionalUser=  userRepository.findByEmail(signUpRequest.getEmail());
      if(optionalUser.isPresent()){
          System.out.println();
        User user = optionalUser.get();

       String  password =user.getPassword();
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
          System.out.println(user);

          return authenticateUser(new LoginRequest(user.getName(), signUpRequest.getPassword()),password);
      }
        System.out.println(optionalUser);
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }


}
