package untitled.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import untitled.config.kafka.KafkaProcessor;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    StatisticsRepository statisticsRepository;

    @Autowired
    SystemLogRepository systemLogRepository;

    @Autowired
    AbnormalEventRepository abnormalEventRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentDeleted'"
    )
    public void wheneverContentDeleted_LogAndStatOnContentDeletion(
        @Payload ContentDeleted contentDeleted
    ) {
        ContentDeleted event = contentDeleted;
        System.out.println(
            "\n\n##### listener LogAndStatOnContentDeletion : " +
            contentDeleted +
            "\n\n"
        );

        // Comments //
        //콘텐츠 삭제 이벤트가 발생할 때마다 시스템 운영 현황 파악 및 추적을 위해 시스템 모니터링 컨텍스트에서 로그 및 통계를 생성해야 합니다. (Monitoring-Log 연계)

        // Sample Logic //
        SystemLog.logAndStatOnContentDeletion(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentRegistered'"
    )
    public void wheneverContentRegistered_LogAndStatOnContentRegistration(
        @Payload ContentRegistered contentRegistered
    ) {
        ContentRegistered event = contentRegistered;
        System.out.println(
            "\n\n##### listener LogAndStatOnContentRegistration : " +
            contentRegistered +
            "\n\n"
        );

        // Comments //
        //콘텐츠 관리 컨텍스트에서 콘텐츠 등록 이벤트가 발생하면, 시스템 운영 현황 파악 및 추적을 위해 시스템 모니터링 컨텍스트에서 로그 및 통계를 생성해야 합니다. (Monitoring-Log 연계)

        // Sample Logic //
        SystemLog.logAndStatOnContentRegistration(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentModified'"
    )
    public void wheneverContentModified_LogAndStatOnContentModification(
        @Payload ContentModified contentModified
    ) {
        ContentModified event = contentModified;
        System.out.println(
            "\n\n##### listener LogAndStatOnContentModification : " +
            contentModified +
            "\n\n"
        );

        // Comments //
        //콘텐츠 수정 이벤트가 발생할 때마다 시스템 운영 현황 파악 및 추적을 위해 시스템 모니터링 컨텍스트에서 로그 및 통계를 생성해야 합니다. (Monitoring-Log 연계)

        // Sample Logic //
        SystemLog.logAndStatOnContentModification(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
