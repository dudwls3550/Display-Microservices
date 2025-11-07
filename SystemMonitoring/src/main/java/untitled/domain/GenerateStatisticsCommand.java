package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class GenerateStatisticsCommand {

    private StatisticsType statisticsType;
    private String aggregationPeriod;
    private ContentId contentId;
}
