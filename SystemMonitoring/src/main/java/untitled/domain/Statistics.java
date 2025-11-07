package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.SystemMonitoringApplication;

@Entity
@Table(name = "Statistics_table")
@Data
//<<< DDD / Aggregate Root
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long statisticsId;

    private Date generatedAt;

    private StatisticsType statisticsType;

    private String aggregationPeriod;

    private String data;

    private ContentId contentId;

    public static StatisticsRepository repository() {
        StatisticsRepository statisticsRepository = SystemMonitoringApplication.applicationContext.getBean(
            StatisticsRepository.class
        );
        return statisticsRepository;
    }

    //<<< Clean Arch / Port Method
    public void generateStatistics(
        GenerateStatisticsCommand generateStatisticsCommand
    ) {
        //implement business logic here:

        StatisticsGenerated statisticsGenerated = new StatisticsGenerated(this);
        statisticsGenerated.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
