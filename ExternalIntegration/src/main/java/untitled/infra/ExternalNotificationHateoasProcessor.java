package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class ExternalNotificationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<ExternalNotification>> {

    @Override
    public EntityModel<ExternalNotification> process(
        EntityModel<ExternalNotification> model
    ) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/retrynotification"
                )
                .withRel("retrynotification")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "sendexternalnotification"
                )
                .withRel("endexternalnotification")
        );

        return model;
    }
}
