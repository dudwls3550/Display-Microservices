package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RequestContentApprovalCommand {

    private Long contentId;
    private Long requesterId;
}
