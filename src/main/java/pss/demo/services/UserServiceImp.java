package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pss.demo.models.Delegation;
import pss.demo.models.User;
import pss.demo.models.dto.UserDTO;
import pss.demo.repositorys.UserRepository;
import pss.demo.services.interfaces.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {


    UserRepository userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public List<User> getAllByRoleName(String roleName) {
//        return userRepository.findAllByRoleName(roleName);
//    }

    @Override
    public void set(UserDTO userDTO) {
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
        }

    }

    @Override
    public void deleteById(int userId) {
         userRepository.deleteById(userId);
    }

    @Override
    @Modifying
    public void set(int userId, Delegation delegation) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.getDelegationSet().add(delegation);
            userRepository.save(user);
        }

    }
}
