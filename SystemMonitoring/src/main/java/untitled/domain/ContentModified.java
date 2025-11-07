package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class ContentModified extends AbstractEvent {

    private Long contentId;
    private Object metadata;
    private String fileUrl;
    private Date updatedAt;
    private Object status;
}
