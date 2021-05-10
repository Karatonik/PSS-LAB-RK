package pss.demo.services.interfaces;


import io.swagger.models.auth.In;
import org.springframework.web.filter.DelegatingFilterProxy;
import pss.demo.models.Delegation;

import javax.mail.MessagingException;
import java.util.List;

public interface DelegationService {



    Delegation get(int delegationId);

    //f
    boolean remove(int userId, int delegationId);

    //g
    Delegation change(int DelegationId, Delegation delegation);

    //h
    List<Delegation> getAll();

    //i
    List<Delegation> getAllOrderByDateStartDesc();

    //j
    List<Delegation> getAllByUserOrderByDateStartDesc(int userId);

    //
    Delegation set (Delegation delegation);
    void deleteById(Integer id);

    String changeAcceptDelegation(Integer delegationId , Integer userId);

    String changeFinishedEdition(Integer delegationId) throws MessagingException;


}
