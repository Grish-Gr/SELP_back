package grsh.grdv.SELP_back.repositories;

import grsh.grdv.SELP_back.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
}
