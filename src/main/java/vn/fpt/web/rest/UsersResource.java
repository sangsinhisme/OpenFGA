package vn.fpt.web.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import vn.fpt.models.users.IamPagination;
import vn.fpt.models.users.IamUserInfo;
import vn.fpt.services.client.IamClientService;
import vn.fpt.web.exceptions.ErrorResponse;

@Slf4j
@Path("/users")
@Tag(name = "Users Management", description = "Users Management AI Camera Service")
public class UsersResource {

    @RestClient
    IamClientService iamClient;

    @GET
    @Operation(
            operationId = "getUsers",
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
    public Response getUsers(
            @QueryParam("first") Integer first,
            @QueryParam("max") Integer max,
            @QueryParam("app") String app,
            @QueryParam("order") String order) {

        String token = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJfei0tTW5aZGpfV0lKNjdIYTlKSmUwOVpnMzV5LTdhZnR6RWJmNm1SNUZFIn0.eyJleHAiOjE3MTAxODY3NDIsImlhdCI6MTcxMDE0MzU0MiwianRpIjoiYTRhMTI2ZTYtZGI1Yy00MmRjLTlmMTMtZjM1NWFiNGJmMGFjIiwiaXNzIjoiaHR0cHM6Ly9kZXYtY2Fkc2hvdXNlLmZwdC52bi9pYW0tYWRtaW4vYXV0aC9yZWFsbXMvbWFzdGVyIiwic3ViIjoiYzM1MDhiNTUtN2VhMi00NGI0LWE3YzAtMjJkMzA0OGJkN2I5IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYWRtaW4tY2xpIiwic2Vzc2lvbl9zdGF0ZSI6IjZjODcwOGRkLWEwMTgtNDZlOC1iNWFlLWQ1YzQ3Mzg5MmZmOCIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiIl0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsInNpZCI6IjZjODcwOGRkLWEwMTgtNDZlOC1iNWFlLWQ1YzQ3Mzg5MmZmOCIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiS2hvYSBWdSBEYW5nIiwicHJlZmVycmVkX3VzZXJuYW1lIjoia2hvYXZkMTIiLCJnaXZlbl9uYW1lIjoiS2hvYSIsImZhbWlseV9uYW1lIjoiVnUgRGFuZyIsImVtYWlsIjoia2hvYXZkMTJAZnB0LmNvbSJ9.oSFU_aDtN_AMFRm8WtJOjNOy_9RSUWulDdT1z8meoSvhbnBm4ER2fwXsc98hnDtMNzuhzRyCXRYMxz1DBii_qRcuXpbrDTLsZgS118d6MYusIUd0P0TdBSoRlf527jbHQF5J3S7-bRhYUlvfw0KlmKNzQIIJS8u00cHJxInr2PqS9tINCuU1RPn7YMqyPMxUvPJEzWAIPdQ1VKd4mvx5ANWrS_GnjQ6eN6WAq_raNMdzvHwfO1cbQjFlgNqo8N_c6BY4OaBZUmHuoi2lwPPVJce_jtaTV7PUYp94sTxfRfpnJHC3RYJw2zjf2_lEQ7pqiOQzA6XxUD044q0x9VaYmg";
        IamPagination<IamUserInfo> users = iamClient.getUsers(token, first, max, app, order);
        return Response
                .ok(users)
                .build();
    }

    @GET
    @Path(("/{id}"))
    @Operation(
            operationId = "getUserById",
            summary = "Get details by User ID of AI Camera Service"
    )
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = IamUserInfo.class)
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
    public Response getUserById(@PathParam("id") String id,
                                @QueryParam("app") String app) {

        String token = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJfei0tTW5aZGpfV0lKNjdIYTlKSmUwOVpnMzV5LTdhZnR6RWJmNm1SNUZFIn0.eyJleHAiOjE3MTAxODY3NDIsImlhdCI6MTcxMDE0MzU0MiwianRpIjoiYTRhMTI2ZTYtZGI1Yy00MmRjLTlmMTMtZjM1NWFiNGJmMGFjIiwiaXNzIjoiaHR0cHM6Ly9kZXYtY2Fkc2hvdXNlLmZwdC52bi9pYW0tYWRtaW4vYXV0aC9yZWFsbXMvbWFzdGVyIiwic3ViIjoiYzM1MDhiNTUtN2VhMi00NGI0LWE3YzAtMjJkMzA0OGJkN2I5IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYWRtaW4tY2xpIiwic2Vzc2lvbl9zdGF0ZSI6IjZjODcwOGRkLWEwMTgtNDZlOC1iNWFlLWQ1YzQ3Mzg5MmZmOCIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiIl0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsInNpZCI6IjZjODcwOGRkLWEwMTgtNDZlOC1iNWFlLWQ1YzQ3Mzg5MmZmOCIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiS2hvYSBWdSBEYW5nIiwicHJlZmVycmVkX3VzZXJuYW1lIjoia2hvYXZkMTIiLCJnaXZlbl9uYW1lIjoiS2hvYSIsImZhbWlseV9uYW1lIjoiVnUgRGFuZyIsImVtYWlsIjoia2hvYXZkMTJAZnB0LmNvbSJ9.oSFU_aDtN_AMFRm8WtJOjNOy_9RSUWulDdT1z8meoSvhbnBm4ER2fwXsc98hnDtMNzuhzRyCXRYMxz1DBii_qRcuXpbrDTLsZgS118d6MYusIUd0P0TdBSoRlf527jbHQF5J3S7-bRhYUlvfw0KlmKNzQIIJS8u00cHJxInr2PqS9tINCuU1RPn7YMqyPMxUvPJEzWAIPdQ1VKd4mvx5ANWrS_GnjQ6eN6WAq_raNMdzvHwfO1cbQjFlgNqo8N_c6BY4OaBZUmHuoi2lwPPVJce_jtaTV7PUYp94sTxfRfpnJHC3RYJw2zjf2_lEQ7pqiOQzA6XxUD044q0x9VaYmg";
        IamUserInfo user = iamClient.getUserById(token, id, app);
        return Response
                .ok(user)
                .build();
    }
}
