package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class StatisticsHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Statistics>> {

    @Override
    public EntityModel<Statistics> process(EntityModel<Statistics> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "generatestatistics"
                )
                .withRel("eneratestatistics")
        );

        return model;
    }
}
