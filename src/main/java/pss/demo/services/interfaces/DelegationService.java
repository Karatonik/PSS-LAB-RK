package pss.demo.services.interfaces;


import org.springframework.web.filter.DelegatingFilterProxy;
import pss.demo.models.Delegation;

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
}
