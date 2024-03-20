package vn.fpt.web.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;
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
import vn.fpt.models.auth.DmCUserInfo;
import vn.fpt.models.users.IamPagination;
import vn.fpt.secure.AppSecurityContext;
import vn.fpt.secure.SecurityUtil;
import vn.fpt.services.client.DmcClientService;
import vn.fpt.web.exceptions.ErrorResponse;
import vn.fpt.web.exceptions.ErrorsEnum;
import vn.fpt.web.exceptions.PermissionDeniedException;
import vn.fpt.web.exceptions.UnauthorizedException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Path("/app")
@Tag(name = "Auth Management", description = "Auth Management AI Camera Service")
public class AuthResource {

    @ConfigProperty(name = "application.secret-code")
    String IAM_SECRET;

    @RestClient
    DmcClientService dmcClient;

    @GET
    @Path("/auth")
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
            @Context ContainerRequestContext requestContext,
            @QueryParam("authorizeCode") String authorizeCode
    ) {

        if(authorizeCode == null)
            throw new UnauthorizedException(ErrorsEnum.AUTH_FAILED);

        String encodedCode = URLEncoder.encode(authorizeCode, StandardCharsets.UTF_8);

        DecryptAuth deAuth = new DecryptAuth();
        deAuth.setEncryptedToken(encodedCode);
        deAuth.setSecretCode(IAM_SECRET);

        AuthToken authToken = dmcClient.decryptToken(deAuth);

        String token = authToken.getToken();

        if(token.isBlank())
            throw new UnauthorizedException(ErrorsEnum.AUTH_FAILED);
        else {

            DmCUserInfo userInfo = dmcClient.getUserPermission("churn", "cads", token);

            if(SecurityUtil.isUserHasPermission("churn", userInfo)) {

                requestContext.setSecurityContext(new AppSecurityContext(userInfo));

                NewCookie cookie = new NewCookie("accessToken", token);

                return Response
                        .ok()
                        .entity(userInfo)
                        .cookie(cookie)
                        .build();
            } else throw new PermissionDeniedException(ErrorsEnum.AUTH_NO_ACCESS);
        }
    }

    @GET
    @Path("/logout")
    @Operation(
            operationId = "logout",
            summary = "User logout of AI Camera Service"
    )
    @APIResponse(
            responseCode = "200"
    )
    @APIResponse(
            responseCode = "500",
            description = "",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    public Response logout(
            @Context ContainerRequestContext requestContext
    ) throws URISyntaxException {

        NewCookie removeCookie = new NewCookie("accessToken", "");

        return Response
                .status(Response.Status.PERMANENT_REDIRECT)
                .location(new URI("/"))
                .cookie(removeCookie)
                .build();
    }
}