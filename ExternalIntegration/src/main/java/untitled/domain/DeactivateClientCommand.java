package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DeactivateClientCommand {

    private Long clientId;
    private String reason;
}
