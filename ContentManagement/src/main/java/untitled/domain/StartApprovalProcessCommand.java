package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class StartApprovalProcessCommand {

    private Long contentId;
    private Long requestedBy;
}
