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
// @RequestMapping(value="/users")
@Transactional
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(
        value = "/usersregisteruser",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public User registerUser(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RegisterUserCommand registerUserCommand
    ) throws Exception {
        System.out.println("##### /user/registerUser  called #####");
        User user = new User();
        user.registerUser(registerUserCommand);
        userRepository.save(user);
        return user;
    }

    @RequestMapping(
        value = "/users/{id}/grantuserpermission",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public User grantUserPermission(
        @PathVariable(value = "id") Long id,
        @RequestBody GrantUserPermissionCommand grantUserPermissionCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/grantUserPermission  called #####");
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.grantUserPermission(grantUserPermissionCommand);

        userRepository.save(user);
        return user;
    }

    @RequestMapping(
        value = "/users/{id}/updateuserdetails",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public User updateUserDetails(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateUserDetailsCommand updateUserDetailsCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/updateUserDetails  called #####");
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.updateUserDetails(updateUserDetailsCommand);

        userRepository.save(user);
        return user;
    }

    @RequestMapping(
        value = "/users/{id}/activateuser",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public User activateUser(
        @PathVariable(value = "id") Long id,
        @RequestBody ActivateUserCommand activateUserCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/activateUser  called #####");
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.activateUser(activateUserCommand);

        userRepository.save(user);
        return user;
    }

    @RequestMapping(
        value = "/users/{id}/deactivateuser",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public User deactivateUser(
        @PathVariable(value = "id") Long id,
        @RequestBody DeactivateUserCommand deactivateUserCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/deactivateUser  called #####");
        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.deactivateUser(deactivateUserCommand);

        userRepository.save(user);
        return user;
    }
}
//>>> Clean Arch / Inbound Adaptor
