package vn.fpt.services.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestForm;
import vn.fpt.models.users.IamPagination;
import vn.fpt.models.users.IamUserInfo;
import vn.fpt.models.users.UpdateUserDetails;

@RegisterRestClient
public interface IamClientService {

    /**
     * API create a User.
     *
     * @param token header for request.
     * @param id the arg of request.
     * @param app the arg of request.
     * @param dto body of request.
     *
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
     * @param id the arg of request.
     * @param app the arg of request.
     * @param dto body of request.
     *
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
     * @param max query param of request.
     * @param order query param of request.
     *
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
     * @param id the arg of request.
     * @param app the arg of request.
     *
     * @return AuthToken.
     */
    @GET
    @Path("/cads/users/{id}")
    IamUserInfo getUserById(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                           @PathParam("id") String id,
                                           @QueryParam("app") String app);

}
