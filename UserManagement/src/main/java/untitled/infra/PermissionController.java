package untitled.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/permissions")
@Transactional
public class PermissionController {

    @Autowired
    PermissionRepository permissionRepository;

    @RequestMapping(
        value = "/permissionsdefinepermission",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Permission definePermission(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody DefinePermissionCommand definePermissionCommand
    ) throws Exception {
        System.out.println("##### /permission/definePermission  called #####");
        Permission permission = new Permission();
        permission.definePermission(definePermissionCommand);
        permissionRepository.save(permission);
        return permission;
    }

    @RequestMapping(
        value = "/permissions/{id}/updatepermissiondefinition",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Permission updatePermissionDefinition(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdatePermissionDefinitionCommand updatePermissionDefinitionCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /permission/updatePermissionDefinition  called #####"
        );
        Optional<Permission> optionalPermission = permissionRepository.findById(
            id
        );

        optionalPermission.orElseThrow(() -> new Exception("No Entity Found"));
        Permission permission = optionalPermission.get();
        permission.updatePermissionDefinition(
            updatePermissionDefinitionCommand
        );

        permissionRepository.save(permission);
        return permission;
    }
}
//>>> Clean Arch / Inbound Adaptor
