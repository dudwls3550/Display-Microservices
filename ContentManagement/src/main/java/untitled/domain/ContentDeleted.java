package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ContentDeleted extends AbstractEvent {

    private Long contentId;
    private Date deletedAt;

    public ContentDeleted(Content aggregate) {
        super(aggregate);
    }

    public ContentDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
