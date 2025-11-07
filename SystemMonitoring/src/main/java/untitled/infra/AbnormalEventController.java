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
// @RequestMapping(value="/abnormalEvents")
@Transactional
public class AbnormalEventController {

    @Autowired
    AbnormalEventRepository abnormalEventRepository;

    @RequestMapping(
        value = "/abnormalEventsrecordabnormalevent",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public AbnormalEvent recordAbnormalEvent(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RecordAbnormalEventCommand recordAbnormalEventCommand
    ) throws Exception {
        System.out.println(
            "##### /abnormalEvent/recordAbnormalEvent  called #####"
        );
        AbnormalEvent abnormalEvent = new AbnormalEvent();
        abnormalEvent.recordAbnormalEvent(recordAbnormalEventCommand);
        abnormalEventRepository.save(abnormalEvent);
        return abnormalEvent;
    }

    @RequestMapping(
        value = "/abnormalEvents/{id}/resolveabnormalevent",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public AbnormalEvent resolveAbnormalEvent(
        @PathVariable(value = "id") String id,
        @RequestBody ResolveAbnormalEventCommand resolveAbnormalEventCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /abnormalEvent/resolveAbnormalEvent  called #####"
        );
        Optional<AbnormalEvent> optionalAbnormalEvent = abnormalEventRepository.findById(
            id
        );

        optionalAbnormalEvent.orElseThrow(() -> new Exception("No Entity Found")
        );
        AbnormalEvent abnormalEvent = optionalAbnormalEvent.get();
        abnormalEvent.resolveAbnormalEvent(resolveAbnormalEventCommand);

        abnormalEventRepository.save(abnormalEvent);
        return abnormalEvent;
    }
}
//>>> Clean Arch / Inbound Adaptor
