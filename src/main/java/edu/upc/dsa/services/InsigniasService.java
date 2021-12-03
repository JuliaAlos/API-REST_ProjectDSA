package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Insignia;
import edu.upc.dsa.models.Plane;
import edu.upc.dsa.transferObjects.InsigniaUserTO;
import edu.upc.dsa.transferObjects.PlaneUserTO;
import edu.upc.dsa.transferObjects.UserTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/insignias", description = "Endpoint to the Insignias Service")
@Path("/insignias")
public class InsigniasService {
    private GameManager manager;

    public InsigniasService() {
        this.manager = GameManagerImpl.getInstance();

        if (manager.getAll().size() == 0) {
            GameManagerImpl.getInstance().addUser("Juls2000", "12345", "Júlia Alós", "julia.alos@estudiantat.upc.edu");
            GameManagerImpl.getInstance().addUser("PauEmperador", "12345", "Pau Baguer", "pau.baguer@upc.edu");
            manager.addUser("Arnau", "12345", "ArnauMir", "arnau.mir@upc.edu");
            Plane plane0 = new Plane("Cessna", 100, 100, 9.81, 2, 10);
            Plane plane1 = new Plane("AirbusA320", 500, 70, 10.0, 5, 80);
            Insignia insignia0 = new Insignia("FirstFlight", "03/12/2021", "Progressive");
            Insignia insignia1 = new Insignia("FighterMaster", "03/12/2021", "Skills");
            manager.addPlane(plane0);
            manager.addPlane(plane1);
            manager.addInsignia(insignia0);
            manager.addInsignia(insignia1);

            manager.addPlaneToUser("Arnau", "Cessna");
            manager.addPlaneToUser("Arnau", "Airbus A320");
            manager.addInsigniaToUser("Arnau", "First Flight");
        }
    }

    @POST
    @ApiOperation(value = "Add insignia to the system", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Plane successfully added to the system.")

    })

    @Path("/addInsigniaToSystem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInsignia(Insignia insignia) {
        this.manager.addInsignia(insignia);
        return Response.status(201).build();
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get all insignias in the system.", notes = "asdasd")
    @ApiResponses(value = {
                @ApiResponse(code = 200, message = "OK", response = Insignia.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "No insignias in the system yet.")
    })
    @Path("/GetAllInsignias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInsignias() {
        List<Insignia> listInsignia = this.manager.getAllInsignias();
        GenericEntity<List<Insignia>> entity = new GenericEntity<List<Insignia>>(listInsignia){};
        if (listInsignia.size() == 0){
            return Response.status(404).build();
        }
        else{
            return Response.status(200).entity(entity).build();
        }
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get insignia by its name.", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response=Insignia.class),
            @ApiResponse(code = 404, message = "Not found (insignia not found)")
    })
    @Path("/getInsigniaByName/{insigniaName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInsigniaByName (@PathParam("insigniaName") String insigniaName) {
        Insignia insignia = this.manager.getInsigniaByName(insigniaName);
        GenericEntity<Insignia> entity = new GenericEntity<Insignia>(insignia){};
        if (this.manager.existInsignia(insigniaName)){
            return Response.status(200).entity(entity).build();
        }
        else{
            return Response.status(404).build();
        }

    }

    //------------------------------

    @POST
    @ApiOperation(value = "Add an insignia to a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added"),
            @ApiResponse(code = 409, message = "No insignia or user found in the system.")

    })

    @Path("/addInsigniaToUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInsigniaToUser(InsigniaUserTO insigniaUserTO) {
        if (this.manager.existInsignia(insigniaUserTO.getUserName()) && (this.manager.existPlane(insigniaUserTO.getInsigniaName()))){
            this.manager.addInsigniaToUser(insigniaUserTO.getUserName(),insigniaUserTO.getInsigniaName());
            return Response.status(201).build();
        }
        else{
            return Response.status(409).build();
        }
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get the list of insignias of a user.", notes = "asdasd", response = Insignia.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The player has no insignias yet.")
    })
    @Path("/getListInsigniasUser/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListsInsigniasUser (@PathParam("username") String username) {
        List <Insignia> listInsigniaUser = this.manager.getListInsigniasUser(username);
        GenericEntity<List<Insignia>> entity = new GenericEntity<List<Insignia>>(listInsigniaUser){};
        if (listInsigniaUser.size() == 0){
            return Response.status(404).build();
        }
        else{
            return Response.status(200).entity(entity).build();
        }
    }

}
