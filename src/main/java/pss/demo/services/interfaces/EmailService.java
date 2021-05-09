package pss.demo.services.interfaces;

import javax.mail.MessagingException;

public interface EmailService {


    void finishEditionDelegationMail(int delegationId) throws MessagingException;

    void confirmationDelegationMail(int delegationId);

    void sendConfirmation(int userId);









}
