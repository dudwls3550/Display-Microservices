package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class AbnormalEventAlertQuery {

    private AbnormalEventStatus status;
    private EventSeverity severity;
}
