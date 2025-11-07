package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ContentSearchIndexQuery {

    private String searchTerm;
    private String category;
    private List<String> tags;
}
