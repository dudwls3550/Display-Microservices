package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RejectContentCommand {

    private Long approvalId;
    private Long rejectedBy;
    private String rejectionReason;
}
