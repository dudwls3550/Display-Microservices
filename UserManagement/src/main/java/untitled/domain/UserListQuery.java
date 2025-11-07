package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class UserListQuery {

    private UserRole role;
    private UserStatus status;
}
