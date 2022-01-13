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
        User newUser = user.toUser();
        this.manager.addUser(newUser);
        this.managerPlanes.addPlaneToPlayer("Cessna",user.getUserName());
        this.managerInsignias.addInsigniaToPlayer("Welcome", user.getUserName());

        UserTO userTO = new UserTO(newUser);
        return Response.status(201).entity(userTO).build();
    }

    @POST
    @ApiOperation(value = "Update existing user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create",response=UserTO.class),
            @ApiResponse(code = 404, message = "User not registered")

    })

    @Path("/update/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(RegisterUserTO user, @PathParam("userName") String userName) {
        if (!manager.existUser(userName))  return Response.status(404).build();
        User newUser = user.toUser();
        this.manager.updateUser(newUser, userName);

        UserTO userTO = new UserTO(newUser);
        return Response.status(201).entity(userTO).build();
    }

    @POST
    @ApiOperation(value = "Post game earnings", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create"),
            @ApiResponse(code = 404, message = "User not registered")

    })

    @Path("/uploadGame/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response uploadGame(GameResults gameResults, @PathParam("userName") String userName) {
        if (!manager.existUser(userName))  return Response.status(404).build();
        this.manager.syncGameResults(gameResults, userName);


        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "Post new money after buying item", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create"),
            @ApiResponse(code = 404, message = "User not registered")

    })

    @Path("/syncBitcoins/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response uploadGame(SetBitcoinsTO bitcoins, @PathParam("userName") String userName) {
        if (!manager.existUser(userName))  return Response.status(404).build();
        this.manager.setMoney(bitcoins.getBitcoins(), userName);


        return Response.status(201).build();
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


    @GET
    @ApiOperation(value = "Get all users sorted by achieved distance", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RankingTO.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "Not found (no users registered)")
    })
    @Path("/getByDistance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDistance(){

        List<User> userList = this.manager.sortByDistance();
        List<RankingTO> userTOList = new LinkedList<RankingTO>();
        Integer pos = 1;
        for(User user:userList){
            userTOList.add(new RankingTO(user.getUserName(),pos.toString(),user.getPlayer().getMaxDistance().toString(),user.getImage_url(),user.getPlayer().getRol()));
            pos++;
        }
        GenericEntity<List<RankingTO>> entity = new GenericEntity<List<RankingTO>>(userTOList) {};

        if(userTOList.size() > 0)
            return Response.status(200).entity(entity).build();
        return Response.status(404).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Get all users sorted by time played", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RankingTO.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "Not found (no users registered)")
    })
    @Path("/getByTime")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByTime(){

        List<User> userList = this.manager.sortByTime();
        List<RankingTO> userTOList = new LinkedList<RankingTO>();
        Integer pos=1;
        for(User user:userList){
            userTOList.add(new RankingTO(user.getUserName(),pos.toString(),user.getPlayer().getTimeOfFlight().toString(),user.getImage_url(),user.getPlayer().getRol()));
            pos++;
        }
        GenericEntity<List<RankingTO>> entity = new GenericEntity<List<RankingTO>>(userTOList) {};

        if(userTOList.size() > 0)
            return Response.status(200).entity(entity).build();
        return Response.status(404).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Get all users sorted by money", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RankingTO.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "Not found (no users registered)")
    })
    @Path("/getByMoney")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByMoney(){

        List<User> userList = this.manager.sortByMoney();
        List<RankingTO> userTOList = new LinkedList<RankingTO>();
        Integer pos =1;
        for(User user:userList){
            userTOList.add(new RankingTO(user.getUserName(),pos.toString(),user.getPlayer().getBitcoins().toString(),user.getImage_url(),user.getPlayer().getRol()));
            pos++;
        }
        GenericEntity<List<RankingTO>> entity = new GenericEntity<List<RankingTO>>(userTOList) {};

        if(userTOList.size() > 0)
            return Response.status(200).entity(entity).build();
        return Response.status(404).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Get all users sorted by their rol", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RankingTO.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "Not found")
    })
    @Path("/getByRol")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByRol(){

        List<User> userList = this.manager.sortByRol();
        List<RankingTO> userTOList = new LinkedList<RankingTO>();
        Integer pos=1;
        for(User user:userList){
            userTOList.add(new RankingTO(user.getUserName(),pos.toString(),user.getPlayer().getRol(),user.getImage_url(),user.getPlayer().getRol()));
            pos++;
        }
        GenericEntity<List<RankingTO>> entity = new GenericEntity<List<RankingTO>>(userTOList) {};

        if(userTOList.size() > 0)
            return Response.status(200).entity(entity).build();
        return Response.status(404).entity(entity).build();
    }


    @GET
    @ApiOperation(value = "Get position user sorted by achieved distance", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RankingTO.class),
            @ApiResponse(code = 404, message= "Not found")
    })
    @Path("/getByDistance/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDistance(@PathParam("userName") String userName){

        List<User> userList = this.manager.sortByDistance();
        Integer pos=1;
        for(User user:userList){
            if(user.getUserName().equals(userName)){
                RankingTO response = new RankingTO(userName,pos.toString(),user.getPlayer().getMaxDistance().toString(),user.getImage_url(),user.getPlayer().getRol());
                return Response.status(200).entity(response).build();
            }
            pos++;
        }
        return Response.status(404).build();
    }

    @GET
    @ApiOperation(value = "Get position user sorted by time played", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RankingTO.class),
            @ApiResponse(code = 404, message= "Not found")
    })
    @Path("/getByTime/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTime(@PathParam("userName") String userName){

        List<User> userList = this.manager.sortByTime();
        Integer pos=1;
        for(User user:userList){
            if(user.getUserName().equals(userName)){
                RankingTO response = new RankingTO(userName,pos.toString(),user.getPlayer().getTimeOfFlight().toString(),user.getImage_url(),user.getPlayer().getRol());
                return Response.status(200).entity(response).build();
            }
            pos++;
        }
        return Response.status(404).build();
    }

    @GET
    @ApiOperation(value = "Get position user sorted by money", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RankingTO.class),
            @ApiResponse(code = 404, message= "Not found")
    })
    @Path("/getByMoney/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoney(@PathParam("userName") String userName){

        List<User> userList = this.manager.sortByMoney();
        Integer pos=1;
        for(User user:userList){
            if(user.getUserName().equals(userName)){
                RankingTO response = new RankingTO(userName,pos.toString(),user.getPlayer().getBitcoins().toString(),user.getImage_url(),user.getPlayer().getRol());
                return Response.status(200).entity(response).build();
            }
            pos++;
        }
        return Response.status(404).build();
    }

    @GET
    @ApiOperation(value = "Get position user sorted by their rol", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RankingTO.class),
            @ApiResponse(code = 404, message= "Not found (no users registered)")
    })
    @Path("/getByRol/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRol(@PathParam("userName") String userName){

        List<User> userList = this.manager.sortByRol();
        Integer pos=1;
        for(User user:userList){
            if(user.getUserName().equals(userName)){
                RankingTO response = new RankingTO(userName,pos.toString(),user.getPlayer().getRol(),user.getImage_url(),user.getPlayer().getRol());
                return Response.status(200).entity(response).build();
            }
            pos++;
        }
        return Response.status(404).build();
    }


}





