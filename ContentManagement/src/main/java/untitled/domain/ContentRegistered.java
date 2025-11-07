package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ContentRegistered extends AbstractEvent {

    private Long contentId;
    private Long ownerId;
    private ContentMetadata metadata;
    private String fileUrl;
    private Date createdAt;
    private ContentStatus status;

    public ContentRegistered(Content aggregate) {
        super(aggregate);
    }

    public ContentRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
