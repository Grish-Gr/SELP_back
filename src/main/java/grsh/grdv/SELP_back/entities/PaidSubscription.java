package grsh.grdv.SELP_back.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "paid_subscription")
public class PaidSubscription {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "code")
    private PaidSubscriptionCode code;

    @OneToMany(mappedBy = "paidSubscription")
    private List<User> users;
}
