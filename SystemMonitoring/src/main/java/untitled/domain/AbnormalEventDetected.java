package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class AbnormalEventDetected extends AbstractEvent {

    private Long abnormalEventId;
    private Date detectedAt;
    private SystemLogId systemLogId;
    private String symptom;
    private EventSeverity severity;
    private AbnormalEventStatus status;
    private UserId userId;
    private Boolean notificationSent;

    public AbnormalEventDetected(AbnormalEvent aggregate) {
        super(aggregate);
    }

    public AbnormalEventDetected() {
        super();
    }
}
//>>> DDD / Domain Event
