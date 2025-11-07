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
@Table(name = "ExternalNotification_table")
@Data
//<<< DDD / Aggregate Root
public class ExternalNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;

    private ContentId contentId;

    private Long externalSystemId;

    private NotificationChannel channel;

    private NotificationStatus status;

    private String metadata;

    private Date notifiedAt;

    private Date createdAt;

    private Date updatedAt;

    private ExternalIntegrationClientId externalIntegrationClientId;

    private MetadataId metadataId;

    public static ExternalNotificationRepository repository() {
        ExternalNotificationRepository externalNotificationRepository = ExternalIntegrationApplication.applicationContext.getBean(
            ExternalNotificationRepository.class
        );
        return externalNotificationRepository;
    }

    //<<< Clean Arch / Port Method
    public void retryNotification(
        RetryNotificationCommand retryNotificationCommand
    ) {
        //implement business logic here:

        ExternalSystemNotified externalSystemNotified = new ExternalSystemNotified(
            this
        );
        externalSystemNotified.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void sendExternalNotification(
        SendExternalNotificationCommand sendExternalNotificationCommand
    ) {
        //implement business logic here:

        ExternalSystemNotified externalSystemNotified = new ExternalSystemNotified(
            this
        );
        externalSystemNotified.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void contentPublicationNotification(
        ContentPublished contentPublished
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        ExternalNotification externalNotification = new ExternalNotification();
        repository().save(externalNotification);

        ExternalSystemNotified externalSystemNotified = new ExternalSystemNotified(externalNotification);
        externalSystemNotified.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if contentPublished.metadataIdcontentApprovalIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> contentMap = mapper.convertValue(contentPublished.getMetadataId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentPublished.getContentApprovalId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentPublished.getUserId(), Map.class);

        repository().findById(contentPublished.get???()).ifPresent(externalNotification->{
            
            externalNotification // do something
            repository().save(externalNotification);

            ExternalSystemNotified externalSystemNotified = new ExternalSystemNotified(externalNotification);
            externalSystemNotified.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void contentRegistrationNotification(
        ContentRegistered contentRegistered
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        ExternalNotification externalNotification = new ExternalNotification();
        repository().save(externalNotification);

        ExternalSystemNotified externalSystemNotified = new ExternalSystemNotified(externalNotification);
        externalSystemNotified.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if contentRegistered.metadataIdcontentApprovalIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> contentMap = mapper.convertValue(contentRegistered.getMetadataId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentRegistered.getContentApprovalId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentRegistered.getUserId(), Map.class);

        repository().findById(contentRegistered.get???()).ifPresent(externalNotification->{
            
            externalNotification // do something
            repository().save(externalNotification);

            ExternalSystemNotified externalSystemNotified = new ExternalSystemNotified(externalNotification);
            externalSystemNotified.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void notifyOnAbnormalEvent(
        AbnormalEventDetected abnormalEventDetected
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        ExternalNotification externalNotification = new ExternalNotification();
        repository().save(externalNotification);

        ExternalSystemNotified externalSystemNotified = new ExternalSystemNotified(externalNotification);
        externalSystemNotified.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if abnormalEventDetected.userIdsystemLogId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> abnormalEventMap = mapper.convertValue(abnormalEventDetected.getUserId(), Map.class);
        // Map<Long, Object> abnormalEventMap = mapper.convertValue(abnormalEventDetected.getSystemLogId(), Map.class);

        repository().findById(abnormalEventDetected.get???()).ifPresent(externalNotification->{
            
            externalNotification // do something
            repository().save(externalNotification);

            ExternalSystemNotified externalSystemNotified = new ExternalSystemNotified(externalNotification);
            externalSystemNotified.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void externalNotificationOnApproval(
        ContentApproved contentApproved
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        ExternalNotification externalNotification = new ExternalNotification();
        repository().save(externalNotification);

        ExternalSystemNotified externalSystemNotified = new ExternalSystemNotified(externalNotification);
        externalSystemNotified.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if contentApproved.userId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> contentApprovalMap = mapper.convertValue(contentApproved.getUserId(), Map.class);

        repository().findById(contentApproved.get???()).ifPresent(externalNotification->{
            
            externalNotification // do something
            repository().save(externalNotification);

            ExternalSystemNotified externalSystemNotified = new ExternalSystemNotified(externalNotification);
            externalSystemNotified.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
