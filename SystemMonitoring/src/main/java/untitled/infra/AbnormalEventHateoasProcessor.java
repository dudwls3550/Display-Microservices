package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class AbnormalEventHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<AbnormalEvent>> {

    @Override
    public EntityModel<AbnormalEvent> process(
        EntityModel<AbnormalEvent> model
    ) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "recordabnormalevent"
                )
                .withRel("ecordabnormalevent")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/resolveabnormalevent"
                )
                .withRel("resolveabnormalevent")
        );

        return model;
    }
}
