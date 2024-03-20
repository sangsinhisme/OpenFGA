package vn.fpt.services.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import vn.fpt.models.auth.*;
import vn.fpt.models.users.IamPagination;
import vn.fpt.models.users.IamUserInfo;
import vn.fpt.models.users.UpdateUserDetails;

@RegisterRestClient
public interface IamClientService {

    /**
     * API create a User.
     *
     * @param token header for request.
     * @param id    the arg of request.
     * @param app   query param of request.
     * @param dto   body of request.
     * @return IamUserInfo.
     */
    @POST
    @Path("/cads/users/{id}")
    IamUserInfo createUser(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                           @PathParam("id") String id,
                           @QueryParam("app") String app,
                           @RequestBody UpdateUserDetails dto);

    /**
     * API update a User info.
     *
     * @param token header for request.
     * @param id    the arg of request.
     * @param app   query param of request.
     * @param dto   body of request.
     * @return IamUserInfo.
     */
    @PUT
    @Path("/cads/users/{id}")
    IamUserInfo updateUserByID(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                               @PathParam("id") String id,
                               @QueryParam("app") String app,
                               @RequestBody UpdateUserDetails dto);

    /**
     * API get list Users.
     *
     * @param token header for request.
     * @param first query param of request.
     * @param max   query param of request.
     * @param order query param of request.
     * @return IamPagination<IamUserInfo>.
     */
    @GET
    @Path("/cads/users")
    IamPagination<IamUserInfo> getUsers(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                        @QueryParam("first") Integer first,
                                        @QueryParam("max") Integer max,
                                        @QueryParam("app") String app,
                                        @QueryParam("order") String order);

    /**
     * API get a User Details.
     *
     * @param token header for request.
     * @param id    the arg of request.
     * @param app   query param of request.
     * @return AuthToken.
     */
    @GET
    @Path("/cads/users/{id}")
    IamUserInfo getUserById(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                            @PathParam("id") String id,
                            @QueryParam("app") String app);

    /**
     * API check exist a User in Tenant.
     *
     * @param token  header for request.
     * @param search query param of request.
     * @param app    query param of request.
     * @return AuthToken.
     */
    @GET
    @Path("/cads/users/check")
    IamUserInfo check(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                      @QueryParam("app") String app,
                      @QueryParam("search") String search);

    /**
     * API create a Resource.
     *
     * @param token header for request.
     * @param dto   body of request.
     * @return AuthToken.
     */
    @POST
    @Path("/cads/resources")
    Response createResource(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                            @RequestBody CreateResource dto);

    /**
     * API update a Resource info.
     *
     * @param token header for request.
     * @param id    the arg of request.
     * @param dto   body of request.
     * @return AuthToken.
     */
    @PUT
    @Path("/cads/resources/{id}")
    Response updateResource(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                            @PathParam("id") String id,
                            @QueryParam("app") String app,
                            @RequestBody CreateResource dto);

    /**
     * API get list Resources info.
     *
     * @param token  header for request.
     * @param first  query param of request.
     * @param max    query param of request.
     * @param app    query param of request.
     * @param search query param of request.
     * @param sort   query param of request.
     * @param order  query param of request.
     * @return AuthToken.
     */
    @GET
    @Path("/cads/resources")
    IamPagination<ImaResource> getResources(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                            @QueryParam("first") Integer first,
                                            @QueryParam("max") Integer max,
                                            @QueryParam("app") String app,
                                            @QueryParam("search") String search,
                                            @QueryParam("sort") String sort,
                                            @QueryParam("order") String order);

    /**
     * API get details Resource.
     *
     * @param token header for request.
     * @param id    resource of request.
     * @param app   header param of request.
     * @return ImaResource.
     */
    @GET
    @Path("/cads/resources/{id}")
    ImaResource getResourceById(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                @PathParam("id") String id,
                                @QueryParam("app") String app);

    /**
     * API delete Resource.
     *
     * @param token header for request.
     * @param id    resource of request.
     * @param app   header param of request.
     * @return AuthToken.
     */
    @DELETE
    @Path("/cads/resources/{id}")
    Response deleteResourceById(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                @PathParam("id") String id,
                                @QueryParam("app") String app);

    /**
     * API create a Permission.
     *
     * @param token header for request.
     * @param dto   body of request.
     * @return AuthToken.
     */
    @POST
    @Path("/cads/permissions/autoGenerate")
    Response generatePermission(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                @RequestBody GeneratePermission dto);

    /**
     * API update a Permission info.
     *
     * @param token header for request.
     * @param id    the arg of request.
     * @param dto   body of request.
     * @return AuthToken.
     */
    @PUT
    @Path("/cads/permissions/{id}")
    Response updatePermission(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                              @PathParam("id") String id,
                              @QueryParam("app") String app,
                              @RequestBody CreateResource dto);

    /**
     * API get list Permissions info.
     *
     * @param token  header for request.
     * @param first  query param of request.
     * @param max    query param of request.
     * @param app    query param of request.
     * @param search query param of request.
     * @param sort   query param of request.
     * @param order  query param of request.
     * @return AuthToken.
     */
    @GET
    @Path("/cads/permissions")
    IamPagination<ImaResource> getPermissions(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                              @QueryParam("first") Integer first,
                                              @QueryParam("max") Integer max,
                                              @QueryParam("app") String app,
                                              @QueryParam("search") String search,
                                              @QueryParam("sort") String sort,
                                              @QueryParam("order") String order);

    /**
     * API get details Resources.
     *
     * @param token header for request.
     * @param id    resource of request.
     * @param app   header param of request.
     * @return ImaResource.
     */
    @GET
    @Path("/cads/permissions/{id}")
    ImaResource getPermissionById(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                  @PathParam("id") String id,
                                  @QueryParam("app") String app);

    /**
     * API delete Resources.
     *
     * @param token header for request.
     * @param id    resource of request.
     * @param app   header param of request.
     * @return AuthToken.
     */
    @DELETE
    @Path("/cads/permissions/{id}")
    Response deletePermissionById(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                  @PathParam("id") String id,
                                  @QueryParam("app") String app);


    /**
     * API admin assign Role to User .
     *
     * @param token  header for request.
     * @param userId resource of request.
     * @param dto    body of request.
     * @return AuthToken.
     */
    @PATCH
    @Path("/cads/admin/{userId}/adminRole/in")
    Response assignRole(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                        @PathParam("userId") String userId,
                        @RequestBody AssignAppRole dto);

    /**
     * API admin revoke Role to User .
     *
     * @param token  header for request.
     * @param userId of request.
     * @param dto    body of request.
     * @return AuthToken.
     */
    @PATCH
    @Path("/cads/admin/{userId}/adminRole/out")
    Response revokeRole(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                        @PathParam("userId") String userId,
                        @RequestBody RevokeAppRole dto);


}
