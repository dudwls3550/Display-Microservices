package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "contents", path = "contents")
public interface ContentRepository
    extends PagingAndSortingRepository<Content, Long> {
    @Query(
        value = "select content " +
        "from Content content " +
        "where(:ownerId is null or content.ownerId = :ownerId) and (:status is null or content.status = :status)"
    )
    List<Content> contentList(
        Long ownerId,
        ContentStatus status,
        Pageable pageable
    );

    @Query(
        value = "select content " +
        "from Content content " +
        "where(:searchTerm is null or content.searchTerm like %:searchTerm%) and (:category is null or content.category like %:category%) and (:tags is null or content.tags = :tags)"
    )
    List<Content> contentSearchIndex(
        String searchTerm,
        String category,
        List<String> tags,
        Pageable pageable
    );

    @Query(
        value = "select content " +
        "from Content content " +
        "where(:contentId is null or content.contentId = :contentId)"
    )
    Content contentDetail(Long contentId);
}
