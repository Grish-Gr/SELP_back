package grsh.grdv.SELP_back.service;

import grsh.grdv.SELP_back.entities.User;
import grsh.grdv.SELP_back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).orElseThrow();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
