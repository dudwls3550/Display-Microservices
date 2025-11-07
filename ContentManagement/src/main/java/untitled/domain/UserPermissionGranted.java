package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class UserPermissionGranted extends AbstractEvent {

    private Long userId;
    private Object role;
    private Date updatedAt;
}
