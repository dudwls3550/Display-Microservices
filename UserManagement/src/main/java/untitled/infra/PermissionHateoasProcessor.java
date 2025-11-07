package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class PermissionHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Permission>> {

    @Override
    public EntityModel<Permission> process(EntityModel<Permission> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() + "definepermission"
                )
                .withRel("efinepermission")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/updatepermissiondefinition"
                )
                .withRel("updatepermissiondefinition")
        );

        return model;
    }
}
