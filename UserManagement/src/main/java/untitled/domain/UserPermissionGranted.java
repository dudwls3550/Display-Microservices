package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class UserPermissionGranted extends AbstractEvent {

    private Long userId;
    private UserRole role;
    private Date updatedAt;

    public UserPermissionGranted(User aggregate) {
        super(aggregate);
    }

    public UserPermissionGranted() {
        super();
    }
}
//>>> DDD / Domain Event
