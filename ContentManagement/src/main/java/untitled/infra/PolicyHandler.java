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
    MetadataRepository metadataRepository;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    ContentApprovalRepository contentApprovalRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='UserRegistered'"
    )
    public void wheneverUserRegistered_UserRegistrationSync(
        @Payload UserRegistered userRegistered
    ) {
        UserRegistered event = userRegistered;
        System.out.println(
            "\n\n##### listener UserRegistrationSync : " +
            userRegistered +
            "\n\n"
        );

        // Comments //
        //사용자 관리 컨텍스트에서 신규 사용자가 등록되면, 콘텐츠 관리 컨텍스트는 해당 사용자의 접근 권한 및 역할을 반영하기 위해 관련 메타데이터를 업데이트해야 합니다. 이는 컨텍스트 간의 접근 권한 동기화 요구사항을 충족합니다.

        // Sample Logic //
        Metadata.userRegistrationSync(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentModified'"
    )
    public void wheneverContentModified_AutoApprovalStartOnModification(
        @Payload ContentModified contentModified
    ) {
        ContentModified event = contentModified;
        System.out.println(
            "\n\n##### listener AutoApprovalStartOnModification : " +
            contentModified +
            "\n\n"
        );

        // Comments //
        //기존 콘텐츠가 수정되면, 변경 사항에 대해 다시 승인 절차를 거쳐야 하므로 ContentApproval 애그리게이트에서 승인 프로세스를 재시작합니다.

        // Sample Logic //
        ContentApproval.autoApprovalStartOnModification(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='UserPermissionGranted'"
    )
    public void wheneverUserPermissionGranted_UserPermissionSync(
        @Payload UserPermissionGranted userPermissionGranted
    ) {
        UserPermissionGranted event = userPermissionGranted;
        System.out.println(
            "\n\n##### listener UserPermissionSync : " +
            userPermissionGranted +
            "\n\n"
        );

        // Comments //
        //관리자가 사용자에게 권한을 부여하면, 콘텐츠 관리 컨텍스트는 변경된 접근 권한을 반영하여 콘텐츠 접근 제어를 유지해야 합니다. 이는 권한 변경 이벤트를 구독하여 접근 권한을 동기화하라는 요구사항을 이행합니다.

        // Sample Logic //
        Metadata.userPermissionSync(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentApproved'"
    )
    public void wheneverContentApproved_PublicationOnApproval(
        @Payload ContentApproved contentApproved
    ) {
        ContentApproved event = contentApproved;
        System.out.println(
            "\n\n##### listener PublicationOnApproval : " +
            contentApproved +
            "\n\n"
        );

        // Comments //
        //관리자에 의해 콘텐츠가 승인되면, 해당 콘텐츠는 Content 애그리게이트에서 시스템 내에 공개 상태로 전환되어 일반 사용자가 접근할 수 있도록 배포되어야 합니다.

        // Sample Logic //
        Content.publicationOnApproval(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ContentRegistered'"
    )
    public void wheneverContentRegistered_AutoApprovalStartOnRegistration(
        @Payload ContentRegistered contentRegistered
    ) {
        ContentRegistered event = contentRegistered;
        System.out.println(
            "\n\n##### listener AutoApprovalStartOnRegistration : " +
            contentRegistered +
            "\n\n"
        );

        // Comments //
        //콘텐츠가 등록되면, 승인 대기 상태로 전환되며, 전용 ContentApproval 애그리게이트에서 승인 프로세스를 시작해야 합니다.

        // Sample Logic //
        ContentApproval.autoApprovalStartOnRegistration(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
