package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.ContentManagementApplication;

@Entity
@Table(name = "ContentApproval_table")
@Data
//<<< DDD / Aggregate Root
public class ContentApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long approvalId;

    private Long contentId;

    private Long requestedBy;

    private Date requestedAt;

    private ApprovalStatus approvalStatus;

    private ApprovalHistory approvalHistory;

    private UserId userId;

    public static ContentApprovalRepository repository() {
        ContentApprovalRepository contentApprovalRepository = ContentManagementApplication.applicationContext.getBean(
            ContentApprovalRepository.class
        );
        return contentApprovalRepository;
    }

    //<<< Clean Arch / Port Method
    public void startApprovalProcess(
        StartApprovalProcessCommand startApprovalProcessCommand
    ) {
        //implement business logic here:

        ApprovalProcessStarted approvalProcessStarted = new ApprovalProcessStarted(
            this
        );
        approvalProcessStarted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void approveContent(ApproveContentCommand approveContentCommand) {
        //implement business logic here:

        ContentApproved contentApproved = new ContentApproved(this);
        contentApproved.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void rejectContent(RejectContentCommand rejectContentCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void autoApprovalStartOnModification(
        ContentModified contentModified
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        ContentApproval contentApproval = new ContentApproval();
        repository().save(contentApproval);

        ApprovalProcessStarted approvalProcessStarted = new ApprovalProcessStarted(contentApproval);
        approvalProcessStarted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if contentModified.metadataIdcontentApprovalIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> contentMap = mapper.convertValue(contentModified.getMetadataId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentModified.getContentApprovalId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentModified.getUserId(), Map.class);

        repository().findById(contentModified.get???()).ifPresent(contentApproval->{
            
            contentApproval // do something
            repository().save(contentApproval);

            ApprovalProcessStarted approvalProcessStarted = new ApprovalProcessStarted(contentApproval);
            approvalProcessStarted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void autoApprovalStartOnRegistration(
        ContentRegistered contentRegistered
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        ContentApproval contentApproval = new ContentApproval();
        repository().save(contentApproval);

        ApprovalProcessStarted approvalProcessStarted = new ApprovalProcessStarted(contentApproval);
        approvalProcessStarted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if contentRegistered.metadataIdcontentApprovalIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> contentMap = mapper.convertValue(contentRegistered.getMetadataId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentRegistered.getContentApprovalId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentRegistered.getUserId(), Map.class);

        repository().findById(contentRegistered.get???()).ifPresent(contentApproval->{
            
            contentApproval // do something
            repository().save(contentApproval);

            ApprovalProcessStarted approvalProcessStarted = new ApprovalProcessStarted(contentApproval);
            approvalProcessStarted.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
