package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.ExternalIntegrationApplication;

@Entity
@Table(name = "ExternalIntegrationClient_table")
@Data
//<<< DDD / Aggregate Root
public class ExternalIntegrationClient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;

    private String clientName;

    private ClientType type;

    private AuthToken authToken;

    private ClientStatus status;

    private Date registeredAt;

    private Date updatedAt;

    private Date lastAccessedAt;

    public static ExternalIntegrationClientRepository repository() {
        ExternalIntegrationClientRepository externalIntegrationClientRepository = ExternalIntegrationApplication.applicationContext.getBean(
            ExternalIntegrationClientRepository.class
        );
        return externalIntegrationClientRepository;
    }

    //<<< Clean Arch / Port Method
    public void updateClientDetails(
        UpdateClientDetailsCommand updateClientDetailsCommand
    ) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void registerClient(RegisterClientCommand registerClientCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void deactivateClient(
        DeactivateClientCommand deactivateClientCommand
    ) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
