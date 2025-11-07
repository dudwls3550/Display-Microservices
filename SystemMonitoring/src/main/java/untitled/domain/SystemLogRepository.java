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
    collectionResourceRel = "systemLogs",
    path = "systemLogs"
)
public interface SystemLogRepository
    extends PagingAndSortingRepository<SystemLog, Long> {
    @Query(
        value = "select systemLog " +
        "from SystemLog systemLog " +
        "where(:startDate is null or systemLog.startDate = :startDate) and (:endDate is null or systemLog.endDate = :endDate) and (:userId is null or systemLog.userId = :userId) and (:eventType is null or systemLog.eventType = :eventType)"
    )
    List<SystemLog> systemLogList(
        Date startDate,
        Date endDate,
        Long userId,
        LogType eventType,
        Pageable pageable
    );

    @Query(
        value = "select systemLog " +
        "from SystemLog systemLog " +
        "where(:logId is null or systemLog.logId = :logId)"
    )
    SystemLog systemLogDetail(Long logId);
}
