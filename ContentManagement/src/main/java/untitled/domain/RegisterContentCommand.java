package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RegisterContentCommand {

    private Long ownerId;
    private ContentMetadata metadata;
    private String fileUrl;
}
