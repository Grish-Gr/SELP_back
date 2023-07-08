package grsh.grdv.SELP_back.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "verification_tokens")
public class VerificationToken {

    @Id
    @Column(name="confirmed_token")
    private String verificationToken;

    @Column(name = "email")
    private String emailUser;
}
