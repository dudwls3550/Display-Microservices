package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class MetadataUpdated extends AbstractEvent {

    private Long metadataId;
    private String title;
    private String description;
    private ContentCategory category;
    private List<Tag> tags;

    public MetadataUpdated(Metadata aggregate) {
        super(aggregate);
    }

    public MetadataUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
