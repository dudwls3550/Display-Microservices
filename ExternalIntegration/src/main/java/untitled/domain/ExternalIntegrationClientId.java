package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

//<<< DDD / Value Object
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalIntegrationClientId {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private ClientType type;
}
//>>> DDD / Value Object
