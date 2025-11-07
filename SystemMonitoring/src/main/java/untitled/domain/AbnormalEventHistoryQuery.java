package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class AbnormalEventHistoryQuery {

    private Date startDate;
    private Date endDate;
    private UserId userId;
    private AbnormalEventStatus status;
}
