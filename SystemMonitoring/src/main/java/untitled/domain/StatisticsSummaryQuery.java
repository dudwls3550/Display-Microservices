package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class StatisticsSummaryQuery {

    private StatisticsType statisticsType;
    private Integer limit;
}
