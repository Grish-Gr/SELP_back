package grsh.grdv.SELP_back.service;

import grsh.grdv.SELP_back.dto.request.LoginFormDto;
import grsh.grdv.SELP_back.dto.response.JwtResponseDto;
import grsh.grdv.SELP_back.entities.User;
import grsh.grdv.SELP_back.repositories.UserRepository;
import grsh.grdv.SELP_back.security.JwtProvider;
import grsh.grdv.SELP_back.security.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtUtils jwtUtils;
    private final HashMap<String, String> storageJwtTokens = new HashMap<>();

    public JwtResponseDto loginInSystem(LoginFormDto loginForm) throws AuthenticationException{
        User user = userRepository.findByEmailIgnoreCase(loginForm.email()).orElseThrow(() -> {
            throw new AuthenticationServiceException("Email or password wrong");
        });
        if (!passwordEncoder.matches(loginForm.password(), user.getPassword())){
            throw new AuthenticationServiceException("Email or password wrong");
        }
        JwtResponseDto jwtTokens = generateTokensByUser(user);
        String userIDInSystem = jwtUtils.getUserIDAuthService(jwtTokens.getRefreshToken());
        storageJwtTokens.put(userIDInSystem, jwtTokens.getRefreshToken());
        return generateTokensByUser(user);
    }

    public JwtResponseDto refreshJwtTokens(String refreshToken
    ) throws AuthenticationServiceException, NoSuchElementException {
        if (!jwtProvider.validateRefreshToken(refreshToken)){
            throw new AuthenticationServiceException("Invalid refresh token");
        }
        String userIDInSystem = jwtUtils.getUserIDAuthService(refreshToken);
        if (!storageJwtTokens.containsKey(userIDInSystem) ||
            !storageJwtTokens.get(userIDInSystem).equals(refreshToken)
        ){
            storageJwtTokens.remove(userIDInSystem);
            throw new AuthenticationServiceException("Token has already been refreshed");
        }
        String newRefreshToken = jwtUtils.generateNewRefreshToken(refreshToken);
        String accessToken = jwtUtils.generateAccessTokenByRefreshToken(refreshToken);
        Long expireTime = jwtUtils.getExpireTimeAccessToken(accessToken);
        String role = jwtUtils.getUserRole(refreshToken);
        storageJwtTokens.replace(userIDInSystem, newRefreshToken);
        return new JwtResponseDto(accessToken, newRefreshToken, expireTime, role);
    }

    private JwtResponseDto generateTokensByUser(User user){
        String refreshToken = jwtProvider.generateRefreshToken(
            user.getId(),
            user.getRole(),
            user.getName(),
            user.getLastname()
        );
        String accessToken = jwtProvider.generateAccessToken(
            user.getId(),
            user.getRole(),
            user.getName(),
            user.getLastname()
        );
        Long expireTime = jwtUtils.getExpireTimeAccessToken(accessToken);
        return new JwtResponseDto(accessToken, refreshToken, expireTime, user.getRole().name());
    }
}
