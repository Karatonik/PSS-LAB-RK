package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
public class UserServiceImp implements UserService {

    DelegationRepository delegationRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserServiceImp(DelegationRepository delegationRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.delegationRepository = delegationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<User> getAllByRoleName(String roleName) {
        Optional<Role> optionalRole = roleRepository.findById(roleName);
        Set<User>userSet = new HashSet<>();
        return optionalRole.map(Role::getUserSet).orElse(userSet);
    }


    @Override
    public void set(User user) {
        userRepository.save(user);
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
            user.setPassword(newPassword);
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

    @Override
    public void addRole(String roleName, int userId) {
        Optional<User>optionalUser = userRepository.findById(userId);
        Optional<Role>optionalRole = roleRepository.findById(roleName);
        if(optionalUser.isPresent()&&optionalRole.isPresent()){
            User  userFound= optionalUser.get();
            Role roleFound = optionalRole.get();
            roleFound.getUserSet().add(userFound);
            userFound.getRoleSet().add(roleFound);
            userRepository.save(userFound);
            roleRepository.save(roleFound);
        }
    }
}
