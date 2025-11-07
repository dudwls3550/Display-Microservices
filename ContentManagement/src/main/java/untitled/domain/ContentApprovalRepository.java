package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "contentApprovals",
    path = "contentApprovals"
)
public interface ContentApprovalRepository
    extends PagingAndSortingRepository<ContentApproval, Long> {
    @Query(
        value = "select contentApproval " +
        "from ContentApproval contentApproval " +
        "where(:approvalStatus is null or contentApproval.approvalStatus = :approvalStatus)"
    )
    List<ContentApproval> pendingApprovalList(
        ApprovalStatus approvalStatus,
        Pageable pageable
    );

    @Query(
        value = "select contentApproval " +
        "from ContentApproval contentApproval " +
        "where(:contentId is null or contentApproval.contentId = :contentId)"
    )
    ContentApproval approvalHistory(Long contentId);
}
