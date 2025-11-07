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
    ExternalNotificationRepository externalNotificationRepository;

    @Autowired
    ExternalIntegrationClientRepository externalIntegrationClientRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentPublished'"
    )
    public void wheneverContentPublished_ContentPublicationNotification(
        @Payload ContentPublished contentPublished
    ) {
        ContentPublished event = contentPublished;
        System.out.println(
            "\n\n##### listener ContentPublicationNotification : " +
            contentPublished +
            "\n\n"
        );

        // Comments //
        //콘텐츠가 최종적으로 배포(Published)되었을 때, 외부 시스템에 해당 변경 사항을 알림으로써 데이터 동기화 및 활용을 가능하게 합니다. 이는 컨텍스트 관계에서 정의된 '등록/배포' 이벤트 연계 패턴을 따릅니다.

        // Sample Logic //
        ExternalNotification.contentPublicationNotification(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentRegistered'"
    )
    public void wheneverContentRegistered_ContentRegistrationNotification(
        @Payload ContentRegistered contentRegistered
    ) {
        ContentRegistered event = contentRegistered;
        System.out.println(
            "\n\n##### listener ContentRegistrationNotification : " +
            contentRegistered +
            "\n\n"
        );

        // Comments //
        //신규 콘텐츠가 등록(Registered)되면, 외부 시스템이 데이터를 동기화할 수 있도록 실시간 알림을 전송해야 한다는 요구사항을 충족합니다. (Content Management -> External Integration)

        // Sample Logic //
        ExternalNotification.contentRegistrationNotification(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AbnormalEventDetected'"
    )
    public void wheneverAbnormalEventDetected_NotifyOnAbnormalEvent(
        @Payload AbnormalEventDetected abnormalEventDetected
    ) {
        AbnormalEventDetected event = abnormalEventDetected;
        System.out.println(
            "\n\n##### listener NotifyOnAbnormalEvent : " +
            abnormalEventDetected +
            "\n\n"
        );

        // Comments //
        //시스템에서 이상 이벤트가 감지되면, 운영 현황을 파악하고 신속히 대응하기 위해 외부 알림 컨텍스트를 통해 알림을 전송해야 합니다. (Monitoring-Notification 연계)

        // Sample Logic //
        ExternalNotification.notifyOnAbnormalEvent(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentApproved'"
    )
    public void wheneverContentApproved_ExternalNotificationOnApproval(
        @Payload ContentApproved contentApproved
    ) {
        ContentApproved event = contentApproved;
        System.out.println(
            "\n\n##### listener ExternalNotificationOnApproval : " +
            contentApproved +
            "\n\n"
        );

        // Comments //
        //콘텐츠 승인(주요 이벤트) 발생 시, ExternalIntegration 컨텍스트로 이벤트를 발행하여 외부 시스템에 실시간 알림을 전송해야 합니다.

        // Sample Logic //
        ExternalNotification.externalNotificationOnApproval(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
