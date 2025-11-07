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
    collectionResourceRel = "permissions",
    path = "permissions"
)
public interface PermissionRepository
    extends PagingAndSortingRepository<Permission, Long> {
    @Query(
        value = "select permission " +
        "from Permission permission " +
        "where(:statusFilter is null or permission.statusFilter = :statusFilter)"
    )
    List<Permission> permissionCatalog(
        PermissionStatus statusFilter,
        Pageable pageable
    );

    @Query(
        value = "select permission " +
        "from Permission permission " +
        "where(:permissionId is null or permission.permissionId = :permissionId)"
    )
    Permission permissionDetail(Long permissionId);
}
