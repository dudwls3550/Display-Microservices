package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class OperationalStatisticsQuery {

    private StatisticsType statisticsType;
    private Date generatedAtStart;
    private Date generatedAtEnd;
}
