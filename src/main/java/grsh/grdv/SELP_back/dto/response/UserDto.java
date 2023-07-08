package grsh.grdv.SELP_back.dto.response;


import grsh.grdv.SELP_back.entities.User;

public record UserDto(long userID, String name, String lastname, String email, String role, Boolean verificationInSystem) {

    public static UserDto from(User user){
        return new UserDto(
            user.getId(),
            user.getName(),
            user.getLastname(),
            user.getEmail(),
            user.getRole().name(),
            user.isVerificationInSystem()
        );
    }
}
