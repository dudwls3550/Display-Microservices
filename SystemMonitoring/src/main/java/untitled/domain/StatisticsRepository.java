package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "statistics",
    path = "statistics"
)
public interface StatisticsRepository
    extends PagingAndSortingRepository<Statistics, Long> {
    @Query(
        value = "select statistics " +
        "from Statistics statistics " +
        "where(:statisticsType is null or statistics.statisticsType = :statisticsType) and (:generatedAtStart is null or statistics.generatedAtStart = :generatedAtStart) and (:generatedAtEnd is null or statistics.generatedAtEnd = :generatedAtEnd)"
    )
    List<Statistics> operationalStatistics(
        StatisticsType statisticsType,
        Date generatedAtStart,
        Date generatedAtEnd,
        Pageable pageable
    );

    @Query(
        value = "select statistics " +
        "from Statistics statistics " +
        "where(:statisticsType is null or statistics.statisticsType = :statisticsType) and (:limit is null or statistics.limit = :limit)"
    )
    List<Statistics> statisticsSummary(
        StatisticsType statisticsType,
        Integer limit,
        Pageable pageable
    );
}
