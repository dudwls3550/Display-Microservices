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
    collectionResourceRel = "externalNotifications",
    path = "externalNotifications"
)
public interface ExternalNotificationRepository
    extends PagingAndSortingRepository<ExternalNotification, Long> {
    @Query(
        value = "select externalNotification " +
        "from ExternalNotification externalNotification " +
        "where(:searchQuery is null or externalNotification.searchQuery like %:searchQuery%) and (:category is null or externalNotification.category like %:category%) and (:externalIntegrationClientId is null or externalNotification.externalIntegrationClientId = :externalIntegrationClientId)"
    )
    List<ExternalNotification> externalContentList(
        String searchQuery,
        String category,
        ExternalIntegrationClientId externalIntegrationClientId,
        Pageable pageable
    );

    @Query(
        value = "select externalNotification " +
        "from ExternalNotification externalNotification " +
        "where(:externalSystemId is null or externalNotification.externalSystemId = :externalSystemId) and (:status is null or externalNotification.status = :status)"
    )
    List<ExternalNotification> notificationLog(
        Long externalSystemId,
        NotificationStatus status,
        Pageable pageable
    );
}
