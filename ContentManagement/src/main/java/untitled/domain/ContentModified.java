package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ContentModified extends AbstractEvent {

    private Long contentId;
    private ContentMetadata metadata;
    private String fileUrl;
    private Date updatedAt;
    private ContentStatus status;

    public ContentModified(Content aggregate) {
        super(aggregate);
    }

    public ContentModified() {
        super();
    }
}
//>>> DDD / Domain Event
