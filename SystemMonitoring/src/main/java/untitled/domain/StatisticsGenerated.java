package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class StatisticsGenerated extends AbstractEvent {

    private Long statisticsId;
    private Date generatedAt;
    private StatisticsType statisticsType;
    private String aggregationPeriod;
    private String data;
    private ContentId contentId;

    public StatisticsGenerated(Statistics aggregate) {
        super(aggregate);
    }

    public StatisticsGenerated() {
        super();
    }
}
//>>> DDD / Domain Event
