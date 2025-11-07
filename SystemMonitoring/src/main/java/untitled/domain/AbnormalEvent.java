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
@Table(name = "AbnormalEvent_table")
@Data
//<<< DDD / Aggregate Root
public class AbnormalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String abnormalEventId;

    private Date detectedAt;

    private Long relatedLogId;

    private String symptom;

    private EventSeverity severity;

    private AbnormalEventStatus status;

    private Boolean notificationSent;

    private SystemLogId systemLogId;

    private UserId userId;

    public static AbnormalEventRepository repository() {
        AbnormalEventRepository abnormalEventRepository = SystemMonitoringApplication.applicationContext.getBean(
            AbnormalEventRepository.class
        );
        return abnormalEventRepository;
    }

    //<<< Clean Arch / Port Method
    public void recordAbnormalEvent(
        RecordAbnormalEventCommand recordAbnormalEventCommand
    ) {
        //implement business logic here:

        AbnormalEventDetected abnormalEventDetected = new AbnormalEventDetected(
            this
        );
        abnormalEventDetected.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void resolveAbnormalEvent(
        ResolveAbnormalEventCommand resolveAbnormalEventCommand
    ) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
