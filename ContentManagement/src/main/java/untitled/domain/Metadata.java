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
@Table(name = "Metadata_table")
@Data
//<<< DDD / Aggregate Root
public class Metadata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long metadataId;

    private Long contentId;

    private String title;

    private String description;

    private ContentCategory category;

    @ElementCollection
    private List<Tag> tags;

    public static MetadataRepository repository() {
        MetadataRepository metadataRepository = ContentManagementApplication.applicationContext.getBean(
            MetadataRepository.class
        );
        return metadataRepository;
    }

    //<<< Clean Arch / Port Method
    public void updateMetadata(UpdateMetadataCommand updateMetadataCommand) {
        //implement business logic here:

        MetadataUpdated metadataUpdated = new MetadataUpdated(this);
        metadataUpdated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void userRegistrationSync(UserRegistered userRegistered) {
        //implement business logic here:

        /** Example 1:  new item 
        Metadata metadata = new Metadata();
        repository().save(metadata);

        MetadataUpdated metadataUpdated = new MetadataUpdated(metadata);
        metadataUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(userRegistered.get???()).ifPresent(metadata->{
            
            metadata // do something
            repository().save(metadata);

            MetadataUpdated metadataUpdated = new MetadataUpdated(metadata);
            metadataUpdated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void userPermissionSync(
        UserPermissionGranted userPermissionGranted
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Metadata metadata = new Metadata();
        repository().save(metadata);

        MetadataUpdated metadataUpdated = new MetadataUpdated(metadata);
        metadataUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(userPermissionGranted.get???()).ifPresent(metadata->{
            
            metadata // do something
            repository().save(metadata);

            MetadataUpdated metadataUpdated = new MetadataUpdated(metadata);
            metadataUpdated.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
