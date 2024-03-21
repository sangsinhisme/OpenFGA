package vn.fpt.web.rest;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import vn.fpt.services.EntityDevicesService;
import vn.fpt.services.dto.CreateEntityDTO;
import vn.fpt.web.errors.models.ErrorResponse;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Path("/api/entity-devices")
@Tag(name = "Entity Devices", description = "Entity Devices Resource")
public class EntityDevicesResource {

    @Inject
    EntityDevicesService entityDevicesService;

    @POST
    @Operation(
            operationId = "createEntityDevice",
            summary = "Create a new Entity Device"
    )
    @APIResponse(
            responseCode = "201",
            description = "",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "500",
            description = "",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    public Response entity(@RequestBody(
            description = "Entity Device to create",
            required = true,
            content = @Content(schema = @Schema(implementation = CreateEntityDTO.class)))
                           @Valid CreateEntityDTO dto,
                           @Context SecurityContext securityContext) throws URISyntaxException {

        entityDevicesService.create(dto);
        return Response
                .created(new URI("/"))
                .build();
    }
}
