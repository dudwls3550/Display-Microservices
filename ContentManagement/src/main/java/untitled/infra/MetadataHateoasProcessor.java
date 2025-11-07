package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class MetadataHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Metadata>> {

    @Override
    public EntityModel<Metadata> process(EntityModel<Metadata> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/updatemetadata")
                .withRel("updatemetadata")
        );

        return model;
    }
}
