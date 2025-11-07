package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ContentPublished extends AbstractEvent {

    private Long contentId;
    private ContentStatus status;

    public ContentPublished(Content aggregate) {
        super(aggregate);
    }

    public ContentPublished() {
        super();
    }
}
//>>> DDD / Domain Event
