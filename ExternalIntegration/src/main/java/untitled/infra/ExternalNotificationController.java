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
// @RequestMapping(value="/externalNotifications")
@Transactional
public class ExternalNotificationController {

    @Autowired
    ExternalNotificationRepository externalNotificationRepository;

    @RequestMapping(
        value = "/externalNotifications/{id}/retrynotification",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public ExternalNotification retryNotification(
        @PathVariable(value = "id") Long id,
        @RequestBody RetryNotificationCommand retryNotificationCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /externalNotification/retryNotification  called #####"
        );
        Optional<ExternalNotification> optionalExternalNotification = externalNotificationRepository.findById(
            id
        );

        optionalExternalNotification.orElseThrow(() ->
            new Exception("No Entity Found")
        );
        ExternalNotification externalNotification = optionalExternalNotification.get();
        externalNotification.retryNotification(retryNotificationCommand);

        externalNotificationRepository.save(externalNotification);
        return externalNotification;
    }

    @RequestMapping(
        value = "/externalNotificationssendexternalnotification",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public ExternalNotification sendExternalNotification(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody SendExternalNotificationCommand sendExternalNotificationCommand
    ) throws Exception {
        System.out.println(
            "##### /externalNotification/sendExternalNotification  called #####"
        );
        ExternalNotification externalNotification = new ExternalNotification();
        externalNotification.sendExternalNotification(
            sendExternalNotificationCommand
        );
        externalNotificationRepository.save(externalNotification);
        return externalNotification;
    }
}
//>>> Clean Arch / Inbound Adaptor
