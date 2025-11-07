package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class GrantUserPermissionCommand {

    private Long userId;
    private UserRole role;
}
