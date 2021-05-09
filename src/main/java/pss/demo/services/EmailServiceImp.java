package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pss.demo.enums.ERole;
import pss.demo.models.Delegation;
import pss.demo.models.Role;
import pss.demo.models.User;
import pss.demo.repositorys.DelegationRepository;
import pss.demo.repositorys.UserRepository;
import pss.demo.services.interfaces.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Set;

@Service
public class EmailServiceImp implements EmailService {

    private JavaMailSender javaMailSender;

    DelegationRepository delegationRepository;

    UserRepository userRepository;

    @Autowired
    public EmailServiceImp(JavaMailSender javaMailSender,DelegationRepository delegationRepository, UserRepository userRepository) {
        this.javaMailSender=javaMailSender;
        this.delegationRepository = delegationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void finishEditionDelegationMail(int delegationId) throws MessagingException {
        Optional<Delegation> optionalDelegation = delegationRepository.findById(delegationId);
        if(optionalDelegation.isPresent()){
            Delegation delegation =optionalDelegation.get();
            if(delegation.isFinishedEdition()){

                Set<User> adminsSet = userRepository.findByRoleSetContaining(new Role(2, ERole.ROLE_ADMIN));


                adminsSet.stream().parallel().forEach(v-> {
                    try {
                        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                        mimeMessageHelper.setTo(v.getEmail());
                        mimeMessageHelper.setSubject("Zakończenie edycji delegacji :" + delegation.getDelegationId());
                        mimeMessageHelper.setText("Edycja delegacji dobiegła końca , proszę o sprawdzenie jej i dokonanie oceny");
                        javaMailSender.send(mimeMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
    @Override
    public void confirmationDelegationMail(int delegationId) {
        Optional<Delegation> optionalDelegation =delegationRepository.findById(delegationId);
        if(optionalDelegation.isPresent()){
            Delegation delegation = optionalDelegation.get();

            if (delegation.isConfirmation()){
                try {

                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                    mimeMessageHelper.setTo(delegation.getUser().getEmail());
                    mimeMessageHelper.setSubject("Zatwierdzono twoją delegację o id :" + delegation.getDelegationId());
                    mimeMessageHelper.setText("Zatwierdziliśmy twój formularz delegacji o id : "+delegation.getDelegationId());
                    javaMailSender.send(mimeMessage);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void sendConfirmation(int userId) {
        Optional<User>optionalUser =userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            try {

                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setTo(user.getEmail());
                mimeMessageHelper.setSubject("Potwierdzenie założenia konta PSS");
                mimeMessageHelper.setText("Kliknij w link by aktywowac konto :http://localhost:8080/activate/"+user.getUserKey());
                javaMailSender.send(mimeMessage);
            }catch (Exception e){
                e.printStackTrace();
            }
        }



    }
}
