package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class ContentHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Content>> {

    @Override
    public EntityModel<Content> process(EntityModel<Content> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/requestcontentapproval"
                )
                .withRel("requestcontentapproval")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "registercontent")
                .withRel("egistercontent")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/publishcontent")
                .withRel("publishcontent")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/modifycontent")
                .withRel("modifycontent")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/deletecontent")
                .withRel("deletecontent")
        );

        return model;
    }
}
