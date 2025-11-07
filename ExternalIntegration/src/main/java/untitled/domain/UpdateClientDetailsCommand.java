package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateClientDetailsCommand {

    private Long clientId;
    private String clientName;
    private ClientType type;
    private AuthToken newAuthToken;
}
