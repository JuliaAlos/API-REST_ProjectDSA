package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Insignia;
import edu.upc.dsa.models.Plane;
import edu.upc.dsa.models.User;
import edu.upc.dsa.transferObjects.PlaneUserTO;
import edu.upc.dsa.transferObjects.RegisterUserTO;
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

@Api(value = "/planes", description = "Endpoint to the Planes Service")
@Path("/planes")
public class PlanesService {

    private GameManager manager;

    public PlanesService() {
        this.manager = GameManagerImpl.getInstance();

        if (manager.getAll().size() == 0) {
            GameManagerImpl.getInstance().addUser("Juls2000", "12345", "Júlia Alós", "julia.alos@estudiantat.upc.edu");
            GameManagerImpl.getInstance().addUser("PauEmperador", "12345", "Pau Baguer", "pau.baguer@upc.edu");
            manager.addUser("Arnau", "12345", "ArnauMir", "arnau.mir@upc.edu");
            Plane plane0 = new Plane("Cessna", 100, 100, 9.81, 2, 10);
            Plane plane1 = new Plane("Airbus A320", 500, 70, 10.0, 5, 80);
            Insignia insignia0 = new Insignia("FirstFlight", "03/12/2021", "Progressive");
            Insignia insignia1 = new Insignia("FighterMaster", "03/12/2021", "Skills");
            manager.addPlane(plane0);
            manager.addPlane(plane1);
            manager.addInsignia(insignia0);
            manager.addInsignia(insignia1);

            manager.addPlaneToUser("Arnau", "Cessna");
            manager.addPlaneToUser("Arnau", "AirbusA320");
            manager.addInsigniaToUser("Arnau", "FirstFlight");
        }
    }

    //------------------------------

    @POST
    @ApiOperation(value = "Add plane to the system", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Plane successfully added to the system.")

    })

    @Path("/addPlaneToSystem")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlane(Plane plane) {
        this.manager.addPlane(plane);
        return Response.status(201).build();
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get all planes in the system.", notes = "asdasd", response = UserTO.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No planes in the system yet.")
    })
    @Path("/GetAllPlanes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlanes() {
        List<Plane> listPlanes = this.manager.getAllPlanes();
        GenericEntity<List<Plane>> entity = new GenericEntity<List<Plane>>(listPlanes){};
        if (listPlanes.size() == 0){
            return Response.status(404).build();
        }
        else{
            return Response.status(200).entity(entity).build();
        }
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get plane by its model.", notes = "asdasd", response = Plane.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found (plane not found)")
    })
    @Path("/getPlaneByModel/{planeModel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaneByModel(@PathParam("planeModel") String planeModel) {
        Plane plane = this.manager.getPlaneByModel(planeModel);
        GenericEntity<Plane> entity = new GenericEntity<Plane>(plane){};
        if (this.manager.existPlane(planeModel)){
            return Response.status(200).entity(entity).build();
        }
        else{
            return Response.status(404).build();
        }
    }

    //------------------------------

    @POST
    @ApiOperation(value = "Add a plane to a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added"),
            @ApiResponse(code = 409, message = "No plane or user found in the system.")

    })

    @Path("/addPlaneToUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaneToUser(PlaneUserTO planeUserTO) {
        if (this.manager.existUser(planeUserTO.getUserName()) && (this.manager.existPlane(planeUserTO.getPlaneModel()))){
            this.manager.addPlaneToUser(planeUserTO.getUserName(),planeUserTO.getPlaneModel());
            return Response.status(201).build();
        }
        else{
            return Response.status(409).build();
        }
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get the list of planes of a user.", notes = "asdasd", response = Plane.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The player has no planes yet.")
    })
    @Path("/getListPlanesUser/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListsPlanesUser (@PathParam("username") String username) {
        List <Plane> listPlanesUser = this.manager.getListPlanesUser(username);
        GenericEntity<List<Plane>> entity = new GenericEntity<List<Plane>>(listPlanesUser){};
        if (listPlanesUser.size() == 0){
            return Response.status(404).build();
        }
        else{
            return Response.status(200).entity(entity).build();
        }
    }



}
