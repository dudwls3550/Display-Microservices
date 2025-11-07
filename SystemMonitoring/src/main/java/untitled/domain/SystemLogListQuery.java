package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class SystemLogListQuery {

    private Date startDate;
    private Date endDate;
    private Long userId;
    private LogType eventType;
}
