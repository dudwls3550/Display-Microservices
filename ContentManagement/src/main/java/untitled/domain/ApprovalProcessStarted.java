package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ApprovalProcessStarted extends AbstractEvent {

    private Long approvalId;
    private Long contentId;
    private Long requestedBy;
    private Date requestedAt;
    private ApprovalStatus approvalStatus;

    public ApprovalProcessStarted(ContentApproval aggregate) {
        super(aggregate);
    }

    public ApprovalProcessStarted() {
        super();
    }
}
//>>> DDD / Domain Event
