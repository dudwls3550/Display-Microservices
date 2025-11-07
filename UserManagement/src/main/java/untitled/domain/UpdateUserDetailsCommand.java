package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateUserDetailsCommand {

    private Long userId;
    private String name;
    private String email;
}
