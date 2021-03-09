package pss.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pss.demo.models.Delegation;
import pss.demo.models.User;

import java.util.List;
import java.util.Set;

@Repository
public interface DelegationRepository extends JpaRepository<Delegation,Integer> {
    public List<Delegation> findAllByUserOrderByDateTimeStartDesc(Integer userId);
    public List<Delegation>findAllByOrderByDateTimeStartDesc();


}
