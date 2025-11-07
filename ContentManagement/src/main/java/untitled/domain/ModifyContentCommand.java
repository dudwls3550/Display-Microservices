package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ModifyContentCommand {

    private Long contentId;
    private ContentMetadata metadata;
    private String fileUrl;
}
