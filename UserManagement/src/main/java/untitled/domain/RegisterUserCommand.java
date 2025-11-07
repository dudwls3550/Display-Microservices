package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RegisterUserCommand {

    private String name;
    private String email;
    private UserRole role;
    private String password;
}
