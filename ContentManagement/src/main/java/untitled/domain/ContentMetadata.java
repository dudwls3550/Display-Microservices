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
public class ContentMetadata {

    private String title;

    private String description;

    private String category;

    @ElementCollection
    @ElementCollection
    private List<String> tags;
}
//>>> DDD / Value Object
