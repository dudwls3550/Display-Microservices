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
    collectionResourceRel = "externalIntegrationClients",
    path = "externalIntegrationClients"
)
public interface ExternalIntegrationClientRepository
    extends PagingAndSortingRepository<ExternalIntegrationClient, Long> {
    @Query(
        value = "select externalIntegrationClient " +
        "from ExternalIntegrationClient externalIntegrationClient " +
        "where(:clientId is null or externalIntegrationClient.clientId = :clientId)"
    )
    ExternalIntegrationClient clientConfiguration(Long clientId);

    @Query(
        value = "select externalIntegrationClient " +
        "from ExternalIntegrationClient externalIntegrationClient " +
        "where(:status is null or externalIntegrationClient.status = :status)"
    )
    List<ExternalIntegrationClient> clientList(
        ClientStatus status,
        Pageable pageable
    );
}
