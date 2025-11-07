package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class SendExternalNotificationCommand {

    private ContentId contentId;
    private ExternalIntegrationClientId externalIntegrationClientId;
    private NotificationChannel channel;
    private MetadataId metadataId;
}
