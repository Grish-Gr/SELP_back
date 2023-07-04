package grsh.grdv.SELP_back.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "sleep_statistics")
public class SleepStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time_sleep")
    private Float timeSleepInHour;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
}
