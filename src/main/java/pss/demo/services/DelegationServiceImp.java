package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.stereotype.Service;
import pss.demo.enums.ERole;
import pss.demo.models.Delegation;
import pss.demo.models.Role;
import pss.demo.models.User;
import pss.demo.repositorys.DelegationRepository;
import pss.demo.repositorys.UserRepository;
import pss.demo.services.interfaces.DelegationService;
import pss.demo.services.interfaces.EmailService;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DelegationServiceImp implements DelegationService {
    UserRepository userRepository;
    DelegationRepository delegationRepository;
    EmailService emailService;

    @Autowired
    public DelegationServiceImp(EmailService emailService,UserRepository userRepository, DelegationRepository delegationRepository) {
        this.userRepository = userRepository;
        this.delegationRepository = delegationRepository;
        this.emailService=emailService;
    }

    @Override
    public Delegation get(int delegationId) {
       Optional<Delegation>optionalDelegation= delegationRepository.findById(delegationId);
        return optionalDelegation.orElse(null);

    }

    @Override
    public boolean remove(int userId, int delegationId) {
        Optional<Delegation> delegationOptional = delegationRepository.findById(delegationId);
        if (delegationOptional.isPresent()) {
            Delegation delegation = delegationOptional.get();
            if (delegation.getUser().getUserId() == userId) {
                delegationRepository.delete(delegation);
                return true;
            }
        }
        return false;
    }

    @Override
    @Modifying
    public Delegation change(int delegationId, Delegation delegation) {
        Optional<Delegation> optionalDelegation = delegationRepository.findById(delegationId);
        if (optionalDelegation.isPresent()) {
            delegation.setDelegationId(delegationId);

            //zmiana edycji na nie zakceptowanej oraz potwierdzenia administratora
            delegation.setFinishedEdition(false);
            delegation.setConfirmation(false);

            delegation.setUser(optionalDelegation.get().getUser());
        return   delegationRepository.save(delegation);
        }
        return new Delegation();
    }

    @Override
    public List<Delegation> getAll() {
        return delegationRepository.findAll();
    }

    @Override
    public List<Delegation> getAllOrderByDateStartDesc() {
        return delegationRepository.findAllByOrderByDateTimeStartDesc();
    }

    @Override
    public List<Delegation> getAllByUserOrderByDateStartDesc(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        List<Delegation> delegationList = new ArrayList<>();
        if (optionalUser.isPresent()) {
            return delegationRepository.findAllByUserOrderByDateTimeStartDesc(optionalUser.get());
        }
        return delegationList;
    }

    @Override
    public Delegation set(Delegation delegation) {
        return delegationRepository.save(delegation);
    }
    //delete
    public void deleteById(Integer id) {
        delegationRepository.deleteById(id);
    }

    @Override
    public String changeAcceptDelegation(Integer delegationId, Integer userId) {
        Optional<Delegation> optionalDelegation = delegationRepository.findById(delegationId);
        if(optionalDelegation.isPresent()){
            Optional<User>optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()){
                User admin =optionalUser.get();
                Set<Role> roles = admin.getRoleSet();
                System.out.println(roles.toString());
                if(roles.toString().contains("ADMIN")){
                    Delegation delegation = optionalDelegation.get();
                    System.out.println("Set zawiera: ");
                    if(delegation.isConfirmation()){
                        delegation.setFinishedEdition(false);
                        delegation.setConfirmation(false);
                        System.out.println("Jest potwierdzone: ");
                    }else if(delegation.isFinishedEdition()){
                        System.out.println("Koniec edycji potwirdzam");
                        delegation.setConfirmation(true);
                        emailService.confirmationDelegationMail(delegation.getDelegationId());
                    }else {

                        return "Delegacja nie jest ukończona";
                    }
                    delegationRepository.save(delegation);
                    return "Zapisano zmaine akcpetacji";
                }
            }
        }
        return "Zmiana nie powiodła się";
    }

    @Override
    public String changeFinishedEdition(Integer delegationId) throws MessagingException {
        Optional<Delegation>optionalDelegation = delegationRepository.findById(delegationId);
        if(optionalDelegation.isPresent()){
            Delegation delegation = optionalDelegation.get();
            if(delegation.isFinishedEdition()){
                delegation.setConfirmation(false);
                delegation.setFinishedEdition(false);
            }else{
                delegation.setFinishedEdition(true);
                emailService.finishEditionDelegationMail(delegation.getDelegationId());
            }
            delegationRepository.save(delegation);
            return "Zapisano";
        }
        return "Nie powiodło się";
    }
}
