package grsh.grdv.SELP_back.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type_paid_subscription")
    private PaidSubscriptionType paidSubscriptionType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<MoodStatistic> moodStatistics;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<SleepStatistic> sleepStatistics;

    @Column(name = "verification")
    private boolean verificationInSystem;
}
