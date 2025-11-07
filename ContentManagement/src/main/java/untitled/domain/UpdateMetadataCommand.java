package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateMetadataCommand {

    private Long metadataId;
    private String title;
    private String description;
    private ContentCategory category;
    private List<Tag> tags;
}
