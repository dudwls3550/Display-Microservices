package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class NotificationLogQuery {

    private Long externalSystemId;
    private NotificationStatus status;
}
