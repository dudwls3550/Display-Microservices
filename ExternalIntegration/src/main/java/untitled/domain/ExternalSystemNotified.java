package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ExternalSystemNotified extends AbstractEvent {

    private Long notificationId;
    private ContentId contentId;
    private Long externalSystemId;
    private NotificationChannel channel;
    private Date notifiedAt;
    private NotificationStatus status;

    public ExternalSystemNotified(ExternalNotification aggregate) {
        super(aggregate);
    }

    public ExternalSystemNotified() {
        super();
    }
}
//>>> DDD / Domain Event
