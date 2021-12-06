package edu.upc.dsa.services;

import edu.upc.dsa.models.*;
import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
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

    public GameService() {
        this.manager = GameManagerImpl.getInstance();

        if (manager.getAll().size()==0) {
            GameManagerImpl.getInstance().addUser("Juls2000","12345","Júlia Alós","julia.alos@estudiantat.upc.edu");
            GameManagerImpl.getInstance().addUser("PauEmperador","12345","Pau Baguer","pau.baguer@upc.edu");
            manager.addUser("Arnau", "12345", "ArnauMir", "arnau.mir@upc.edu");
            Plane plane0 = new Plane("Cessna", 100, 100, 9.81, 2, 10);
            Plane plane1 = new Plane("Airbus A320", 500, 70, 10.0, 5, 80);
            Insignia insignia0 = new Insignia("FirstFlight", "03/12/2021", "Progressive");
            Insignia insignia1 = new Insignia("FighterMaster", "03/12/2021", "Skills");
//            manager.addPlane(plane0);
//            manager.addPlane(plane1);
//            manager.addInsignia(insignia0);
//            manager.addInsignia(insignia1);
//
//            manager.addPlaneToUser("Arnau", "Cessna");
//            manager.addPlaneToUser("Arnau", "AirbusA320");
//            manager.addInsigniaToUser("Arnau", "FirstFlight");
        }

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
        User newUser = new User(user.getUserName(), user.getPassword(), user.getFullName(), user.getEmail());
        this.manager.addUser(newUser);
        UserTO userTO = new UserTO(newUser.getUserName(), newUser.getFullName(), newUser.getEmail(),newUser.getStatus(), newUser.getPlayer());
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
    public Response deleteTrack(@PathParam("userName") String userName) {
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
        UserTO userTO = new UserTO(user.getUserName(),user.getFullName(),user.getEmail(),user.getStatus(), user.getPlayer());
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
            userTOList.add(new UserTO(user.getUserName(),user.getFullName(),user.getEmail(),user.getStatus(), user.getPlayer()));
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
            userTOList.add(new UserTO(user.getUserName(),user.getFullName(),user.getEmail(),user.getStatus(), user.getPlayer()));
        }
        GenericEntity<List<UserTO>> entity = new GenericEntity<List<UserTO>>(userTOList) {};

        if(userTOList.size() > 0)
            return Response.status(200).entity(entity).build();
        return Response.status(404).entity(entity).build();
    }
}





