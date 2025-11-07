package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RecordAbnormalEventCommand {

    private SystemLogId systemLogId;
    private String symptom;
    private EventSeverity severity;
    private UserId userId;
}
