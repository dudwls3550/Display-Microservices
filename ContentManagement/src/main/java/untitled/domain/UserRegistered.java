package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class UserRegistered extends AbstractEvent {

    private Long userId;
    private String name;
    private String email;
    private Object role;
    private Boolean isActive;
    private Date createdAt;
}
