package vn.fpt.web.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import vn.fpt.models.auth.AuthToken;
import vn.fpt.models.auth.DecryptAuth;
import vn.fpt.models.users.IamPagination;
import vn.fpt.services.client.DmcClientService;
import vn.fpt.web.exceptions.ErrorResponse;
import vn.fpt.web.exceptions.ErrorsEnum;
import vn.fpt.web.exceptions.UnauthorizedException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Path("/auth")
@Tag(name = "Auth Management", description = "Auth Management AI Camera Service")
public class AuthResource {

    @ConfigProperty(name = "application.secret-code")
    String IAM_SECRET;

    @RestClient
    DmcClientService dmcClient;

    @GET
    @Operation(
            operationId = "getAuth",
            summary = "Get list Users of AI Camera Service"
    )
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = IamPagination.class)
            )
    )
    @APIResponse(
            responseCode = "500",
            description = "",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    public Response getAuth(
            @QueryParam("authorizeCode") Optional<String> authorizeCode) throws URISyntaxException {

        String code = authorizeCode.orElseThrow(() -> new UnauthorizedException(ErrorsEnum.AUTH_FAILED));
        String encodedCode = URLEncoder.encode(code, StandardCharsets.UTF_8);

        DecryptAuth deAuth = new DecryptAuth();
        deAuth.setEncryptedToken(encodedCode);
        deAuth.setSecretCode(IAM_SECRET);

        AuthToken authToken = dmcClient.decryptToken(deAuth);

        String token = authToken.getToken();
        if(token.isBlank())
            throw new UnauthorizedException(ErrorsEnum.AUTH_FAILED);
        else {

            return Response
                    .temporaryRedirect(new URI(""))
                    .build();

        }
    }
}