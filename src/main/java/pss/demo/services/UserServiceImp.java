package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pss.demo.models.Delegation;
import pss.demo.models.User;
import pss.demo.repositorys.DelegationRepository;
import pss.demo.repositorys.UserRepository;
import pss.demo.services.interfaces.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    DelegationRepository delegationRepository;
    UserRepository userRepository;

    @Autowired
    public UserServiceImp(DelegationRepository delegationRepository, UserRepository userRepository) {
        this.delegationRepository = delegationRepository;
        this.userRepository = userRepository;
    }




    @Override
    public List<User> getAllByRoleName(String roleName) {
        return userRepository.findAllByRoleSetIsLike(roleName);
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
        if(optionalUser.isPresent()){
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
            user.getDelegationSet().add(delegation);
            delegationRepository.save(delegation);
            userRepository.save(user);
        }
    }
}
