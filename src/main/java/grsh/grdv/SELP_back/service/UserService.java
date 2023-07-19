package grsh.grdv.SELP_back.service;

import grsh.grdv.SELP_back.entities.Role;
import grsh.grdv.SELP_back.entities.User;
import grsh.grdv.SELP_back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Value("${selp.secret_key.admin}")
    private String secretKeyAdmin;

    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).orElseThrow();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getPermissionAdmin(Long id, String secretKey) {
        if (secretKey.equals(secretKeyAdmin)) {
            User user = userRepository.findById(id).orElseThrow();
            user.setRole(Role.ADMIN);
            return userRepository.save(user);
        } else {
            throw new AuthenticationServiceException("Secret key failed");
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
