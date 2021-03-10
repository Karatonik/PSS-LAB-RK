package pss.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pss.demo.models.Delegation;
import pss.demo.models.User;
import pss.demo.services.DelegationServiceImp;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/delegations")
public class DelegationController {
    private DelegationServiceImp delegationServiceImp;

    @Autowired
    public DelegationController(DelegationServiceImp delegationServiceImp) {
        this.delegationServiceImp = delegationServiceImp;
    }
    //f
    @DeleteMapping("/remove")
    public void remove(int userId, int delegationId){
        delegationServiceImp.remove(userId,delegationId);
    }
    //g
//    @PutMapping("/change")
//    public void change( @RequestBody Delegation delegation,int delegationId){
//        delegationServiceImp.change(delegationId,delegation);
//    }
    //dla mnie

    @PutMapping("/change")
   public void change( @RequestBody Delegation delegation) {
        delegationServiceImp.saveDel( delegation);
    }




    //h
    @GetMapping("/deleg")
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





}
