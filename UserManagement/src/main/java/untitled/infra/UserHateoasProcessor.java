package untitled.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import untitled.domain.*;

@Component
public class UserHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<User>> {

    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "registeruser")
                .withRel("egisteruser")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/grantuserpermission"
                )
                .withRel("grantuserpermission")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/updateuserdetails"
                )
                .withRel("updateuserdetails")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/activateuser")
                .withRel("activateuser")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/deactivateuser")
                .withRel("deactivateuser")
        );

        return model;
    }
}
