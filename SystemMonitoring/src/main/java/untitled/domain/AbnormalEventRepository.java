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
    collectionResourceRel = "abnormalEvents",
    path = "abnormalEvents"
)
public interface AbnormalEventRepository
    extends PagingAndSortingRepository<AbnormalEvent, String> {
    @Query(
        value = "select abnormalEvent " +
        "from AbnormalEvent abnormalEvent " +
        "where(:startDate is null or abnormalEvent.startDate = :startDate) and (:endDate is null or abnormalEvent.endDate = :endDate) and (:userId is null or abnormalEvent.userId = :userId) and (:status is null or abnormalEvent.status = :status)"
    )
    List<AbnormalEvent> abnormalEventHistory(
        Date startDate,
        Date endDate,
        UserId userId,
        AbnormalEventStatus status,
        Pageable pageable
    );

    @Query(
        value = "select abnormalEvent " +
        "from AbnormalEvent abnormalEvent " +
        "where(:status is null or abnormalEvent.status = :status) and (:severity is null or abnormalEvent.severity = :severity)"
    )
    List<AbnormalEvent> abnormalEventAlert(
        AbnormalEventStatus status,
        EventSeverity severity,
        Pageable pageable
    );
}
