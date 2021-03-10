package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pss.demo.models.Delegation;
import pss.demo.models.User;
import pss.demo.repositorys.DelegationRepository;
import pss.demo.repositorys.UserRepository;
import pss.demo.services.interfaces.DelegationService;

import java.util.List;
import java.util.Optional;

@Service
public class DelegationServiceImp implements DelegationService {
    UserRepository userRepository;
    DelegationRepository delegationRepository;

    @Autowired
    public DelegationServiceImp(UserRepository userRepository, DelegationRepository delegationRepository) {
        this.userRepository = userRepository;
        this.delegationRepository = delegationRepository;
    }

    @Override
    public boolean remove(int userId, int delegationId) {
        Optional<Delegation> delegationOptional = delegationRepository.findById(delegationId);
        if(delegationOptional.isPresent()){
            Delegation delegation = delegationOptional.get();
            if(delegation.getUser().getUserId()==userId){
                delegationRepository.delete(delegation);
                return true;
            }

        }
        return false;

    }

    @Override
    public void change(int delegationId, Delegation delegation) {
        Optional<Delegation> optionalDelegation = delegationRepository.findById(delegationId);
        if(optionalDelegation.isPresent()){
            Delegation delegation1 = optionalDelegation.get();
            delegationRepository.save(delegation1);
        }
    }

//    @Override
//    @Modifying
//    public void change(int DelegationId, Delegation delegation) {
//        Optional<Delegation> delegationOptional = delegationRepository.findById(DelegationId);
//        delegationOptional.ifPresent(value -> delegationRepository.save(value));
//    }



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
        return delegationRepository.findAllByUserOrderByDateTimeStartDesc(userId);
    }

    @Override
    public void saveDel(Delegation delegation) {
        delegationRepository.save(delegation);
    }

//    @Override
//    public void set(int userId, Delegation delegation) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.getDelegationSet().add(delegation);
//            delegationRepository.save(delegation);
//            userRepository.save(user);
//        }
//    }
}
