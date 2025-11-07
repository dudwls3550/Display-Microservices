package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SystemLogGenerated extends AbstractEvent {

    private Long logId;
    private LogType eventType;
    private Date eventTimestamp;
    private UserId userId;
    private Integer level;
    private String description;
    private Date createdAt;
    private Date expiredAt;
    private ContentId contentId;

    public SystemLogGenerated(SystemLog aggregate) {
        super(aggregate);
    }

    public SystemLogGenerated() {
        super();
    }
}
//>>> DDD / Domain Event
