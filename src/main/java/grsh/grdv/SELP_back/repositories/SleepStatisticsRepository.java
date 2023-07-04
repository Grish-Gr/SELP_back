package grsh.grdv.SELP_back.repositories;

import grsh.grdv.SELP_back.entities.SleepStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepStatisticsRepository extends JpaRepository<Long, SleepStatistics> {
}
