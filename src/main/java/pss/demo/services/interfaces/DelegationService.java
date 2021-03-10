package pss.demo.services.interfaces;


import pss.demo.models.Delegation;

import java.util.List;

public interface DelegationService  {

    //f
    boolean remove(int userId, int delegationId);
    //g
    void change(int DelegationId, Delegation delegation);
    //h
    List<Delegation> getAll();
    //i
    List<Delegation> getAllOrderByDateStartDesc();
    //j
    List<Delegation> getAllByUserOrderByDateStartDesc(int userId);
    //dla mnie
    void saveDel(Delegation delegation);



}
