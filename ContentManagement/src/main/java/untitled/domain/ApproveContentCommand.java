package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ApproveContentCommand {

    private Long approvalId;
    private Long approvedBy;
}
