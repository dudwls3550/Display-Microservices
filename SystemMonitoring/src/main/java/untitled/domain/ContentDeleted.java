package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class ContentDeleted extends AbstractEvent {

    private Long contentId;
    private Date deletedAt;
}
