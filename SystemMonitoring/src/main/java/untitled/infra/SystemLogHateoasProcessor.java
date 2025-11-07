package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class SystemLogHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<SystemLog>> {

    @Override
    public EntityModel<SystemLog> process(EntityModel<SystemLog> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "generatesystemlog"
                )
                .withRel("eneratesystemlog")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() + "/deletesystemlog"
                )
                .withRel("deletesystemlog")
        );

        return model;
    }
}
