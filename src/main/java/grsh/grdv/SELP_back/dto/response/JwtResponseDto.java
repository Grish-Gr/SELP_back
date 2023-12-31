package grsh.grdv.SELP_back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponseDto {

    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
    private Long expireTime;
    private String role;
    private String paidSubscriptionCode;
}
