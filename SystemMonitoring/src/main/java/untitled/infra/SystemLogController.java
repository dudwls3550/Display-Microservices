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
// @RequestMapping(value="/systemLogs")
@Transactional
public class SystemLogController {

    @Autowired
    SystemLogRepository systemLogRepository;

    @RequestMapping(
        value = "/systemLogsgeneratesystemlog",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public SystemLog generateSystemLog(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody GenerateSystemLogCommand generateSystemLogCommand
    ) throws Exception {
        System.out.println("##### /systemLog/generateSystemLog  called #####");
        SystemLog systemLog = new SystemLog();
        systemLog.generateSystemLog(generateSystemLogCommand);
        systemLogRepository.save(systemLog);
        return systemLog;
    }

    @RequestMapping(
        value = "/systemLogs/{id}/deletesystemlog",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public SystemLog deleteSystemLog(
        @PathVariable(value = "id") Long id,
        @RequestBody DeleteSystemLogCommand deleteSystemLogCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /systemLog/deleteSystemLog  called #####");
        Optional<SystemLog> optionalSystemLog = systemLogRepository.findById(
            id
        );

        optionalSystemLog.orElseThrow(() -> new Exception("No Entity Found"));
        SystemLog systemLog = optionalSystemLog.get();
        systemLog.deleteSystemLog(deleteSystemLogCommand);

        systemLogRepository.delete(systemLog);
        return systemLog;
    }
}
//>>> Clean Arch / Inbound Adaptor
