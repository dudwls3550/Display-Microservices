package untitled.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/metadata")
@Transactional
public class MetadataController {

    @Autowired
    MetadataRepository metadataRepository;

    @RequestMapping(
        value = "/metadata/{id}/updatemetadata",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Metadata updateMetadata(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateMetadataCommand updateMetadataCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /metadata/updateMetadata  called #####");
        Optional<Metadata> optionalMetadata = metadataRepository.findById(id);

        optionalMetadata.orElseThrow(() -> new Exception("No Entity Found"));
        Metadata metadata = optionalMetadata.get();
        metadata.updateMetadata(updateMetadataCommand);

        metadataRepository.save(metadata);
        return metadata;
    }
}
//>>> Clean Arch / Inbound Adaptor
