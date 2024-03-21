package vn.fpt.web.rest;

import com.google.common.net.HttpHeaders;
import io.minio.errors.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import vn.fpt.services.StreamingService;
import vn.fpt.web.errors.models.ErrorResponse;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Path("/stream")
@Tag(name = "Stream", description = "Streaming Resource")
@AllArgsConstructor
public class StreamingResource {

    private final StreamingService streamingService;

    @Context
    Request request;

    @GET
    @Path("/{bucketName}/{objectName}")
    @APIResponse(
            responseCode = "200",
            description = "",
            content = @Content(mediaType = "video/mp4")
    )
    @APIResponse(
            responseCode = "500",
            description = "",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    public Response streamVideo(
            @PathParam("bucketName") String bucketName,
            @PathParam("objectName") String objectName,
            @HeaderParam(HttpHeaders.RANGE) String rangeHeader) {

        try {

            // Get the size of the object (video)
            long fileSize = streamingService.getSize(bucketName, objectName);
            long startByte = 0;
            long endByte = fileSize - 1;

            // Parse the range header
            if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
                String[] ranges = rangeHeader
                        .substring(6)
                        .split("-");
                startByte = Long.parseLong(ranges[0]);
                if (ranges.length > 1 && !ranges[1].isEmpty()) {
                    endByte = Long.parseLong(ranges[1]);
                }
            }

            // Set the content range header
            String contentRange = String.format("bytes %d-%d/%d", startByte, endByte, fileSize);

            // Set the response status and headers
            Response.ResponseBuilder responseBuilder = Response
                    .status(Response.Status.PARTIAL_CONTENT)
                    .header("Content-Disposition", "inline; filename=\"" + objectName + "\"")
                    .header("Content-Type", MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Range", contentRange)
                    .header("Accept-Ranges", "bytes")
                    .header("Content-Length", endByte - startByte + 1);

            // Get InputStream for the video object from MinIO
            InputStream inputStream = streamingService.getObject(bucketName, objectName, startByte, endByte - startByte + 1);

            // Stream the requested byte range
            return responseBuilder
                    .entity(inputStream)
                    .build();

        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            log.error(e.getMessage(), e);
            return Response
                    .serverError()
                    .build();
        }
    }
}
