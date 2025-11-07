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
// @RequestMapping(value="/contentApprovals")
@Transactional
public class ContentApprovalController {

    @Autowired
    ContentApprovalRepository contentApprovalRepository;

    @RequestMapping(
        value = "/contentApprovalsstartapprovalprocess",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public ContentApproval startApprovalProcess(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody StartApprovalProcessCommand startApprovalProcessCommand
    ) throws Exception {
        System.out.println(
            "##### /contentApproval/startApprovalProcess  called #####"
        );
        ContentApproval contentApproval = new ContentApproval();
        contentApproval.startApprovalProcess(startApprovalProcessCommand);
        contentApprovalRepository.save(contentApproval);
        return contentApproval;
    }

    @RequestMapping(
        value = "/contentApprovals/{id}/approvecontent",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public ContentApproval approveContent(
        @PathVariable(value = "id") Long id,
        @RequestBody ApproveContentCommand approveContentCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /contentApproval/approveContent  called #####"
        );
        Optional<ContentApproval> optionalContentApproval = contentApprovalRepository.findById(
            id
        );

        optionalContentApproval.orElseThrow(() ->
            new Exception("No Entity Found")
        );
        ContentApproval contentApproval = optionalContentApproval.get();
        contentApproval.approveContent(approveContentCommand);

        contentApprovalRepository.save(contentApproval);
        return contentApproval;
    }

    @RequestMapping(
        value = "/contentApprovals/{id}/rejectcontent",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public ContentApproval rejectContent(
        @PathVariable(value = "id") Long id,
        @RequestBody RejectContentCommand rejectContentCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /contentApproval/rejectContent  called #####"
        );
        Optional<ContentApproval> optionalContentApproval = contentApprovalRepository.findById(
            id
        );

        optionalContentApproval.orElseThrow(() ->
            new Exception("No Entity Found")
        );
        ContentApproval contentApproval = optionalContentApproval.get();
        contentApproval.rejectContent(rejectContentCommand);

        contentApprovalRepository.save(contentApproval);
        return contentApproval;
    }
}
//>>> Clean Arch / Inbound Adaptor
