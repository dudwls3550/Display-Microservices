package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class ContentApproved extends AbstractEvent {

    private Long approvalId;
    private Long contentId;
    private Long approvedBy;
    private Date approvedAt;
    private Object approvalStatus;
}
