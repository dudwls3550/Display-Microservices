package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.SystemMonitoringApplication;

@Entity
@Table(name = "SystemLog_table")
@Data
//<<< DDD / Aggregate Root
public class SystemLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logId;

    private LogType eventType;

    private Date eventTimestamp;

    private UserId userId;

    private Integer level;

    private String description;

    private Date createdAt;

    private Date expiredAt;

    private ContentId contentId;

    public static SystemLogRepository repository() {
        SystemLogRepository systemLogRepository = SystemMonitoringApplication.applicationContext.getBean(
            SystemLogRepository.class
        );
        return systemLogRepository;
    }

    //<<< Clean Arch / Port Method
    public void generateSystemLog(
        GenerateSystemLogCommand generateSystemLogCommand
    ) {
        //implement business logic here:

        SystemLogGenerated systemLogGenerated = new SystemLogGenerated(this);
        systemLogGenerated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void deleteSystemLog(DeleteSystemLogCommand deleteSystemLogCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void logAndStatOnContentDeletion(
        ContentDeleted contentDeleted
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        SystemLog systemLog = new SystemLog();
        repository().save(systemLog);

        StatisticsGenerated statisticsGenerated = new StatisticsGenerated(systemLog);
        statisticsGenerated.publishAfterCommit();
        SystemLogGenerated systemLogGenerated = new SystemLogGenerated(systemLog);
        systemLogGenerated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if contentDeleted.metadataIdcontentApprovalIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> contentMap = mapper.convertValue(contentDeleted.getMetadataId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentDeleted.getContentApprovalId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentDeleted.getUserId(), Map.class);

        repository().findById(contentDeleted.get???()).ifPresent(systemLog->{
            
            systemLog // do something
            repository().save(systemLog);

            StatisticsGenerated statisticsGenerated = new StatisticsGenerated(systemLog);
            statisticsGenerated.publishAfterCommit();
            SystemLogGenerated systemLogGenerated = new SystemLogGenerated(systemLog);
            systemLogGenerated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void logAndStatOnContentRegistration(
        ContentRegistered contentRegistered
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        SystemLog systemLog = new SystemLog();
        repository().save(systemLog);

        SystemLogGenerated systemLogGenerated = new SystemLogGenerated(systemLog);
        systemLogGenerated.publishAfterCommit();
        StatisticsGenerated statisticsGenerated = new StatisticsGenerated(systemLog);
        statisticsGenerated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if contentRegistered.metadataIdcontentApprovalIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> contentMap = mapper.convertValue(contentRegistered.getMetadataId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentRegistered.getContentApprovalId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentRegistered.getUserId(), Map.class);

        repository().findById(contentRegistered.get???()).ifPresent(systemLog->{
            
            systemLog // do something
            repository().save(systemLog);

            SystemLogGenerated systemLogGenerated = new SystemLogGenerated(systemLog);
            systemLogGenerated.publishAfterCommit();
            StatisticsGenerated statisticsGenerated = new StatisticsGenerated(systemLog);
            statisticsGenerated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void logAndStatOnContentModification(
        ContentModified contentModified
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        SystemLog systemLog = new SystemLog();
        repository().save(systemLog);

        StatisticsGenerated statisticsGenerated = new StatisticsGenerated(systemLog);
        statisticsGenerated.publishAfterCommit();
        SystemLogGenerated systemLogGenerated = new SystemLogGenerated(systemLog);
        systemLogGenerated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if contentModified.metadataIdcontentApprovalIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> contentMap = mapper.convertValue(contentModified.getMetadataId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentModified.getContentApprovalId(), Map.class);
        // Map<Long, Object> contentMap = mapper.convertValue(contentModified.getUserId(), Map.class);

        repository().findById(contentModified.get???()).ifPresent(systemLog->{
            
            systemLog // do something
            repository().save(systemLog);

            StatisticsGenerated statisticsGenerated = new StatisticsGenerated(systemLog);
            statisticsGenerated.publishAfterCommit();
            SystemLogGenerated systemLogGenerated = new SystemLogGenerated(systemLog);
            systemLogGenerated.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
