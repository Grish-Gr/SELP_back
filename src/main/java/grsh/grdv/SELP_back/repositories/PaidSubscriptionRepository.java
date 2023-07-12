package grsh.grdv.SELP_back.repositories;

import grsh.grdv.SELP_back.entities.PaidSubscription;
import grsh.grdv.SELP_back.entities.PaidSubscriptionCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaidSubscriptionRepository extends JpaRepository<PaidSubscription, Long> {
    Optional<PaidSubscription> findByCode(PaidSubscriptionCode code);
}
