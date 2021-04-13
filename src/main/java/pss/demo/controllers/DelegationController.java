package pss.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pss.demo.models.Delegation;
import pss.demo.models.User;
import pss.demo.services.DelegationServiceImp;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/delegations")
public class DelegationController {
    private DelegationServiceImp delegationServiceImp;

    @Autowired
    public DelegationController(DelegationServiceImp delegationServiceImp) {
        this.delegationServiceImp = delegationServiceImp;
    }
    //f
    @DeleteMapping
    public void remove(int userId, int delegationId){
        delegationServiceImp.remove(userId,delegationId);
    }
    //g
    @PutMapping
    public Delegation change( @RequestBody Delegation delegation,int delegationId){
        return  delegationServiceImp.change(delegationId,delegation);
    }
    //h
    @GetMapping
    public List<Delegation> getAll(){
        return delegationServiceImp.getAll();
    }
    //i
    @GetMapping("/orderByDateStartDesc")
    public List <Delegation> OrderByDateStartDesc(){
        return delegationServiceImp.getAllOrderByDateStartDesc();
    }
    //j
    @GetMapping("/userOrderByDateStartDesc")
    public List<Delegation> UserOrderByDateStartDesc(int userId){
        return delegationServiceImp.getAllByUserOrderByDateStartDesc(userId);
    }
    /*@GetMapping
    public Delegation get(int delegationId){
        return delegationServiceImp.get(delegationId);
    }*/
    //
    @PostMapping("/register")
    public void register(@RequestBody Delegation delegation){
        delegationServiceImp.set(delegation);
    }

    //edit
    @PutMapping("/delegationEdit/{delegation}")
    public ResponseEntity<Delegation> updateDelegation(Delegation delegation){
        return new ResponseEntity<>(delegationServiceImp.set(delegation), HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/deleteDelegations/{id}")
    public ResponseEntity<Void> deleteDelegation(@PathVariable  Integer id){
        delegationServiceImp.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
