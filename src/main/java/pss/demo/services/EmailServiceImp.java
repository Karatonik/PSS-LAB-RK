package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pss.demo.enums.ERole;
import pss.demo.models.Delegation;
import pss.demo.models.Role;
import pss.demo.models.User;
import pss.demo.repositorys.DelegationRepository;
import pss.demo.repositorys.UserRepository;
import pss.demo.services.interfaces.EmailService;

import java.util.Optional;
import java.util.Set;

@Service
public class EmailServiceImp implements EmailService {
    DelegationRepository delegationRepository;

    UserRepository userRepository;

    @Autowired
    public EmailServiceImp(DelegationRepository delegationRepository, UserRepository userRepository) {
        this.delegationRepository = delegationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void finishEditionDelegationMail(int delegationId) {
        Optional<Delegation> optionalDelegation = delegationRepository.findById(delegationId);
        if(optionalDelegation.isPresent()){
            Delegation delegation =optionalDelegation.get();
            if(delegation.isFinishedEdition()){

                Set<User> adminsSet = userRepository.findByRoleSetContaining(new Role(2, ERole.ROLE_ADMIN));


                //todo


            }




        }


    }

    @Override
    public void confirmationDelegationMail(int delegationId) {

    }

    @Override
    public void sendConfirmation(int userId) {

    }
}
