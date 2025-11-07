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
// @RequestMapping(value="/statistics")
@Transactional
public class StatisticsController {

    @Autowired
    StatisticsRepository statisticsRepository;

    @RequestMapping(
        value = "/statisticsgeneratestatistics",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Statistics generateStatistics(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody GenerateStatisticsCommand generateStatisticsCommand
    ) throws Exception {
        System.out.println(
            "##### /statistics/generateStatistics  called #####"
        );
        Statistics statistics = new Statistics();
        statistics.generateStatistics(generateStatisticsCommand);
        statisticsRepository.save(statistics);
        return statistics;
    }
}
//>>> Clean Arch / Inbound Adaptor
