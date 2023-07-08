package grsh.grdv.SELP_back.service;

import grsh.grdv.SELP_back.dto.request.RegistrationFormDto;
import grsh.grdv.SELP_back.entities.Role;
import grsh.grdv.SELP_back.entities.User;
import grsh.grdv.SELP_back.entities.VerificationToken;
import grsh.grdv.SELP_back.repositories.UserRepository;
import grsh.grdv.SELP_back.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Transactional
    public User register(RegistrationFormDto registrationForm){
        if (userRepository.existsByEmailIgnoreCase(registrationForm.email())) {
            throw new AuthenticationServiceException("Other user contains email");
        }
        User user = new User();
        user.setName(registrationForm.name());
        user.setLastname(registrationForm.lastname());
        user.setRole(Role.USER);
        user.setEmail(registrationForm.email());
        user.setPassword(passwordEncoder.encode(registrationForm.password()));
        sendConfirmRegistrationMessage(user);
        return userRepository.save(user);
    }

    @Transactional
    public User confirmUserInSystem(String verificationToken) {
        VerificationToken token = verificationTokenRepository.findById(verificationToken).orElseThrow(() ->
            new AuthenticationServiceException("Verification token is not valid")
        );
        User user = userRepository.findByEmailIgnoreCase(token.getEmailUser()).orElseThrow();
        user.setVerificationInSystem(true);
        return user;
    }

    private void sendConfirmRegistrationMessage(User user){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setVerificationToken(UUID.randomUUID().toString());
        verificationToken.setEmailUser(user.getEmail());
        verificationTokenRepository.save(verificationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
            + "http://localhost:8080/api/v1/auth/confirm-account?token=" + verificationToken.getVerificationToken());
        emailService.sendEmail(mailMessage);
    }
}
