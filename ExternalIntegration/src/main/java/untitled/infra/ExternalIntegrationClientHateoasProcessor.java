package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class ExternalIntegrationClientHateoasProcessor
    implements
        RepresentationModelProcessor<EntityModel<ExternalIntegrationClient>> {

    @Override
    public EntityModel<ExternalIntegrationClient> process(
        EntityModel<ExternalIntegrationClient> model
    ) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/updateclientdetails"
                )
                .withRel("updateclientdetails")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "registerclient")
                .withRel("egisterclient")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/deactivateclient"
                )
                .withRel("deactivateclient")
        );

        return model;
    }
}
