package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.UserManagementApplication;

@Entity
@Table(name = "Permission_table")
@Data
//<<< DDD / Aggregate Root
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long permissionId;

    private UserId userId;

    private PermissionType permissionType;

    private Date grantedAt;

    private PermissionStatus status;

    public static PermissionRepository repository() {
        PermissionRepository permissionRepository = UserManagementApplication.applicationContext.getBean(
            PermissionRepository.class
        );
        return permissionRepository;
    }

    //<<< Clean Arch / Port Method
    public void definePermission(
        DefinePermissionCommand definePermissionCommand
    ) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void updatePermissionDefinition(
        UpdatePermissionDefinitionCommand updatePermissionDefinitionCommand
    ) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
