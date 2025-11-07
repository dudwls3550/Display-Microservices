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
// @RequestMapping(value="/externalIntegrationClients")
@Transactional
public class ExternalIntegrationClientController {

    @Autowired
    ExternalIntegrationClientRepository externalIntegrationClientRepository;

    @RequestMapping(
        value = "/externalIntegrationClients/{id}/updateclientdetails",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public ExternalIntegrationClient updateClientDetails(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateClientDetailsCommand updateClientDetailsCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /externalIntegrationClient/updateClientDetails  called #####"
        );
        Optional<ExternalIntegrationClient> optionalExternalIntegrationClient = externalIntegrationClientRepository.findById(
            id
        );

        optionalExternalIntegrationClient.orElseThrow(() ->
            new Exception("No Entity Found")
        );
        ExternalIntegrationClient externalIntegrationClient = optionalExternalIntegrationClient.get();
        externalIntegrationClient.updateClientDetails(
            updateClientDetailsCommand
        );

        externalIntegrationClientRepository.save(externalIntegrationClient);
        return externalIntegrationClient;
    }

    @RequestMapping(
        value = "/externalIntegrationClientsregisterclient",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public ExternalIntegrationClient registerClient(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RegisterClientCommand registerClientCommand
    ) throws Exception {
        System.out.println(
            "##### /externalIntegrationClient/registerClient  called #####"
        );
        ExternalIntegrationClient externalIntegrationClient = new ExternalIntegrationClient();
        externalIntegrationClient.registerClient(registerClientCommand);
        externalIntegrationClientRepository.save(externalIntegrationClient);
        return externalIntegrationClient;
    }

    @RequestMapping(
        value = "/externalIntegrationClients/{id}/deactivateclient",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public ExternalIntegrationClient deactivateClient(
        @PathVariable(value = "id") Long id,
        @RequestBody DeactivateClientCommand deactivateClientCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /externalIntegrationClient/deactivateClient  called #####"
        );
        Optional<ExternalIntegrationClient> optionalExternalIntegrationClient = externalIntegrationClientRepository.findById(
            id
        );

        optionalExternalIntegrationClient.orElseThrow(() ->
            new Exception("No Entity Found")
        );
        ExternalIntegrationClient externalIntegrationClient = optionalExternalIntegrationClient.get();
        externalIntegrationClient.deactivateClient(deactivateClientCommand);

        externalIntegrationClientRepository.delete(externalIntegrationClient);
        return externalIntegrationClient;
    }
}
//>>> Clean Arch / Inbound Adaptor
