package untitled.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/contents")
@Transactional
public class ContentController {

    @Autowired
    ContentRepository contentRepository;

    @RequestMapping(
        value = "/contents/{id}/requestcontentapproval",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Content requestContentApproval(
        @PathVariable(value = "id") Long id,
        @RequestBody RequestContentApprovalCommand requestContentApprovalCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /content/requestContentApproval  called #####"
        );
        Optional<Content> optionalContent = contentRepository.findById(id);

        optionalContent.orElseThrow(() -> new Exception("No Entity Found"));
        Content content = optionalContent.get();
        content.requestContentApproval(requestContentApprovalCommand);

        contentRepository.save(content);
        return content;
    }

    @RequestMapping(
        value = "/contentsregistercontent",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Content registerContent(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RegisterContentCommand registerContentCommand
    ) throws Exception {
        System.out.println("##### /content/registerContent  called #####");
        Content content = new Content();
        content.registerContent(registerContentCommand);
        contentRepository.save(content);
        return content;
    }

    @RequestMapping(
        value = "/contents/{id}/publishcontent",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Content publishContent(
        @PathVariable(value = "id") Long id,
        @RequestBody PublishContentCommand publishContentCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /content/publishContent  called #####");
        Optional<Content> optionalContent = contentRepository.findById(id);

        optionalContent.orElseThrow(() -> new Exception("No Entity Found"));
        Content content = optionalContent.get();
        content.publishContent(publishContentCommand);

        contentRepository.save(content);
        return content;
    }

    @RequestMapping(
        value = "/contents/{id}/modifycontent",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Content modifyContent(
        @PathVariable(value = "id") Long id,
        @RequestBody ModifyContentCommand modifyContentCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /content/modifyContent  called #####");
        Optional<Content> optionalContent = contentRepository.findById(id);

        optionalContent.orElseThrow(() -> new Exception("No Entity Found"));
        Content content = optionalContent.get();
        content.modifyContent(modifyContentCommand);

        contentRepository.save(content);
        return content;
    }

    @RequestMapping(
        value = "/contents/{id}/deletecontent",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public Content deleteContent(
        @PathVariable(value = "id") Long id,
        @RequestBody DeleteContentCommand deleteContentCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /content/deleteContent  called #####");
        Optional<Content> optionalContent = contentRepository.findById(id);

        optionalContent.orElseThrow(() -> new Exception("No Entity Found"));
        Content content = optionalContent.get();
        content.deleteContent(deleteContentCommand);

        contentRepository.delete(content);
        return content;
    }
}
//>>> Clean Arch / Inbound Adaptor
