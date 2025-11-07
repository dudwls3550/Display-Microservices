package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class ContentApprovalHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<ContentApproval>> {

    @Override
    public EntityModel<ContentApproval> process(
        EntityModel<ContentApproval> model
    ) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "startapprovalprocess"
                )
                .withRel("tartapprovalprocess")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/approvecontent")
                .withRel("approvecontent")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/rejectcontent")
                .withRel("rejectcontent")
        );

        return model;
    }
}
