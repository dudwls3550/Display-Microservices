package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class ContentPublished extends AbstractEvent {

    private Long contentId;
    private Object status;
}
