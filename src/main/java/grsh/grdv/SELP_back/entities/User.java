package grsh.grdv.SELP_back.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthdate")
    private Date birthdate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @ManyToOne()
    @JoinColumn(name = "paid_subscription_id")
    private PaidSubscription paidSubscription;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<MoodStatistic> moodStatistics;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<SleepStatistic> sleepStatistics;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ResultTestStatistic> resultTestStatistics;

    @Column(name = "verification")
    private boolean verificationInSystem;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", birthdate=" + birthdate +
            ", sex=" + sex +
            ", email='" + email + '\'' + '}';
    }
}
