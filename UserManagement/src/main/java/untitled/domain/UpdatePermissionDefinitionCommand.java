package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdatePermissionDefinitionCommand {

    private PermissionType permissionType;
    private String newDescription;
}
