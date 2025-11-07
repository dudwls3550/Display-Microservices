package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public enum LogType {
    SYSTEM_LOG_GENERATED,
    STATISTICS_GENERATED,
    ABNORMAL_EVENT_DETECTED,
}
