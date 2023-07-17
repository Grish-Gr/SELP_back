package grsh.grdv.SELP_back.dto.response;


import grsh.grdv.SELP_back.entities.User;

import java.util.Date;

public record UserDto(long userID, String username,
                      String email, Date birthdate,
                      String role, String paidSubscription,
                      Boolean verificationInSystem
) {

    public static UserDto from(User user){
        return new UserDto(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getBirthdate(),
            user.getRole().name(),
            user.getPaidSubscription().getCode().name(),
            user.isVerificationInSystem()
        );
    }
}
