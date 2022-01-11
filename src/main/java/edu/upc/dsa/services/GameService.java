package edu.upc.dsa.services;

import edu.upc.dsa.*;
import edu.upc.dsa.models.*;
import edu.upc.dsa.transferObjects.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.eclipse.persistence.sessions.server.ClientSession;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/user", description = "Endpoint to Game Service")
@Path("/user")
public class GameService {

    private GameManager manager;
    private PlanesManager managerPlanes;
    private InsigniasManager managerInsignias;

    public GameService() {
        this.manager = GameManagerDAOImpl.getInstance();
        this.managerPlanes = PlanesManagerDAOImpl.getInstance();
        this.managerInsignias = InsigniasManagerDAOImpl.getInstance();


    }

    @POST
    @ApiOperation(value = "Add user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create",response=UserTO.class),
            @ApiResponse(code = 409, message = "Conflict (user already registred)")

    })

    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(RegisterUserTO user) {
        if (manager.existUser(user.getUserName()))  return Response.status(409).build();
        //User newUser = user.toUser();
        this.manager.addUser(user.getUserName(), user.getPassword(), user.getFullName(), user.getEmail(), user.getImage_url());
        this.managerPlanes.addPlaneToPlayer("Cessna",user.getUserName());
        this.managerInsignias.addInsigniaToPlayer("Welcome", user.getUserName());
        User newUser = this.manager.getUser(user.getUserName());
        UserTO userTO = new UserTO(newUser.getUserName(), newUser.getFullName(), newUser.getEmail(),newUser.getStatus(), newUser.getPlayer(), newUser.getImage_url());
        return Response.status(201).entity(userTO).build();
    }


    @POST
    @ApiOperation(value = "Login user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",response=LoginUserTO.class),
            @ApiResponse(code = 404, message = "Not found (user or pass not match)")

    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(LoginUserTO userTO) {
        User user = manager.loginUser(userTO.getUserName(),userTO.getPassword());
        if (user==null)  return Response.status(404).build();
        return Response.status(200).entity(userTO).build();
    }

    //------------------------------

    @DELETE
    @ApiOperation(value = "Delete User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found (user not found)")
    })

    @Path("/{userName}")
    public Response deleteUser(@PathParam("userName") String userName) {
        if (!manager.existUser(userName)) return Response.status(404).build();
        else manager.deleteUser(userName);
        return Response.status(200).build();
    }

    //------------------------------
    @GET
    @ApiOperation(value = "Logout", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found (user not found)")
    })
    @Path("/logout/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarInfoUsuario(@PathParam("userName") String userName) {
        if (!manager.existUser(userName)) return Response.status(404).build();
        else manager.logoutUser(userName);
        return Response.status(200).build();
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get info from user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserTO.class),
            @ApiResponse(code = 404, message = "Not found (user not found)")
    })
    @Path("/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userName") String userName) {
        User user = manager.getUser(userName);
        if (user == null) return Response.status(404).build();
        UserTO userTO = new UserTO(user);
        return Response.status(200).entity(userTO).build();
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get all users sort alphabetically by name", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserTO.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "Not found (no registered users)")
    })
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){

        List<User> userList = this.manager.getAll();
        List<UserTO> userTOList = new LinkedList<UserTO>();
        for(User user:userList){
            userTOList.add(new UserTO(user));
        }
        GenericEntity<List<UserTO>> entity = new GenericEntity<List<UserTO>>(userTOList) {};

        if(userTOList.size() > 0)
            return Response.status(200).entity(entity).build();
        return Response.status(404).entity(entity).build();
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get all active users sort alphabetically by name", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserTO.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "Not found (no active users)")
    })
    @Path("/getAllActive")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActive(){

        List<User> userList = this.manager.getAllActive();
        List<UserTO> userTOList = new LinkedList<UserTO>();
        for(User user:userList){
            userTOList.add(new UserTO(user));
        }
        GenericEntity<List<UserTO>> entity = new GenericEntity<List<UserTO>>(userTOList) {};

        if(userTOList.size() > 0)
            return Response.status(200).entity(entity).build();
        return Response.status(404).entity(entity).build();
    }
}





