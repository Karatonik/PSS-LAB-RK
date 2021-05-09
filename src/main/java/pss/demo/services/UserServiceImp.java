package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pss.demo.enums.ERole;
import pss.demo.models.Delegation;
import pss.demo.models.Role;
import pss.demo.models.User;
import pss.demo.repositorys.DelegationRepository;
import pss.demo.repositorys.RoleRepository;
import pss.demo.repositorys.UserRepository;
import pss.demo.services.interfaces.UserService;

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
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    @Override
    public User setUserAsAdmin(int adminId, int userId) {
        Optional<User> optionalAdmin = userRepository.findById(adminId);
        if (optionalAdmin.isPresent()) {
            System.out.println("admin instnieje");
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                System.out.println("user istnieje");

                User admin = optionalAdmin.get();
                Set<Role> roleSet = admin.getRoleSet();

                if (roleSet.toString().contains("ADMIN")) {
                    System.out.println("admin ma uprawnienia");
                    User user = optionalUser.get();
                    Set<Role> userRoleSet = user.getRoleSet();

                    Role roleAdmin = roleRepository.findByRoleName(ERole.ROLE_ADMIN).get();


                    userRoleSet.add(roleAdmin);

                    user.setRoleSet(userRoleSet);

                    return userRepository.save(user);
                }

            }
        }

        return null;
    }

}
