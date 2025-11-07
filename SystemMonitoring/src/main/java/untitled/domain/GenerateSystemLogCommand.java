package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class GenerateSystemLogCommand {

    private LogType eventType;
    private Date eventTimestamp;
    private UserId userId;
    private Integer level;
    private String description;
    private ContentId contentId;
}
