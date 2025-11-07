package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ExternalContentListQuery {

    private String searchQuery;
    private String category;
    private ExternalIntegrationClientId externalIntegrationClientId;
}
