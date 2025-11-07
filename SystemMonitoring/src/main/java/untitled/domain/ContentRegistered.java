package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class ContentRegistered extends AbstractEvent {

    private Long contentId;
    private Long ownerId;
    private Object metadata;
    private String fileUrl;
    private Date createdAt;
    private Object status;
}
