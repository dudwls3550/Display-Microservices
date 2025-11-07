package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class AbnormalEventDetected extends AbstractEvent {

    private Long abnormalEventId;
    private Date detectedAt;
    private Object systemLogId;
    private String symptom;
    private Object severity;
    private Object status;
    private Object userId;
    private Boolean notificationSent;
}
