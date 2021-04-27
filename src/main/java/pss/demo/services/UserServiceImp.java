package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pss.demo.enums.ERole;
import pss.demo.models.Delegation;
import pss.demo.models.Role;
import pss.demo.models.User;
import pss.demo.repositorys.DelegationRepository;
import pss.demo.repositorys.RoleRepository;
import pss.demo.repositorys.UserRepository;
import pss.demo.services.interfaces.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    PasswordEncoder encoder;
    DelegationRepository delegationRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserServiceImp(DelegationRepository delegationRepository, UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder encoder) {
        this.delegationRepository = delegationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder=encoder;
    }

//    @Override
//    public Set<User> getAllByRoleName(ERole roleName) {
//        Optional<Role> optionalRole = roleRepository.findById(String.valueOf(roleName));
//        Set<User>userSet = new HashSet<>();
//        return optionalRole.map(Role::getUserSet).orElse(userSet);
//    }


    @Override
    public User set(User user) {
       return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void changePassword(int userId, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
        }
    }

    @Override
    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void set(int userId, Delegation delegation) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            delegation.setUser(user);
            delegation = delegationRepository.save(delegation);
            user.getDelegationSet().add(delegation);
            userRepository.save(user);
        }
    }

//    @Override
//    public void addRole(ERole roleName, int userId) {
//        Optional<User>optionalUser = userRepository.findById(userId);
//        Optional<Role>optionalRole = roleRepository.findById(String.valueOf(roleName));
//        if(optionalUser.isPresent()&&optionalRole.isPresent()){
//            User  userFound= optionalUser.get();
//            Role roleFound = optionalRole.get();
//            roleFound.get().add(userFound);
//            userFound.getRoleSet().add(roleFound);
//            userRepository.save(userFound);
//            roleRepository.save(roleFound);
//        }
//    }

    @Override
    public User get(int userId) {
        Optional<User>optionalUser= userRepository.findById(userId);
        return optionalUser.orElse(null);
    }
   
}
