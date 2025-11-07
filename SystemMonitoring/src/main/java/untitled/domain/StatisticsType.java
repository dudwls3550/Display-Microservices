package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public enum StatisticsType {
    SYSTEM_LOAD,
    USER_ACTIVITY,
    CONTENT_USAGE,
}
