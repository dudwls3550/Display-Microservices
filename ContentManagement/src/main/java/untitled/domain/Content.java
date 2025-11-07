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
@Table(name = "Content_table")
@Data
//<<< DDD / Aggregate Root
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contentId;

    private Long ownerId;

    private ContentMetadata metadata;

    private ContentStatus status;

    private String fileUrl;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private ContentApprovalId contentApprovalId;

    private UserId userId;

    private MetadataId metadataId;

    public static ContentRepository repository() {
        ContentRepository contentRepository = ContentManagementApplication.applicationContext.getBean(
            ContentRepository.class
        );
        return contentRepository;
    }

    //<<< Clean Arch / Port Method
    public void requestContentApproval(
        RequestContentApprovalCommand requestContentApprovalCommand
    ) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void registerContent(RegisterContentCommand registerContentCommand) {
        //implement business logic here:

        ContentRegistered contentRegistered = new ContentRegistered(this);
        contentRegistered.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void publishContent(PublishContentCommand publishContentCommand) {
        //implement business logic here:

        ContentPublished contentPublished = new ContentPublished(this);
        contentPublished.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void modifyContent(ModifyContentCommand modifyContentCommand) {
        //implement business logic here:

        ContentModified contentModified = new ContentModified(this);
        contentModified.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void deleteContent(DeleteContentCommand deleteContentCommand) {
        //implement business logic here:

        ContentDeleted contentDeleted = new ContentDeleted(this);
        contentDeleted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void publicationOnApproval(ContentApproved contentApproved) {
        //implement business logic here:

        /** Example 1:  new item 
        Content content = new Content();
        repository().save(content);

        ContentPublished contentPublished = new ContentPublished(content);
        contentPublished.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if contentApproved.userId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> contentApprovalMap = mapper.convertValue(contentApproved.getUserId(), Map.class);

        repository().findById(contentApproved.get???()).ifPresent(content->{
            
            content // do something
            repository().save(content);

            ContentPublished contentPublished = new ContentPublished(content);
            contentPublished.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
