package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ContentApproved extends AbstractEvent {

    private Long approvalId;
    private Long contentId;
    private Long approvedBy;
    private Date approvedAt;
    private ApprovalStatus approvalStatus;

    public ContentApproved(ContentApproval aggregate) {
        super(aggregate);
    }

    public ContentApproved() {
        super();
    }
}
//>>> DDD / Domain Event
