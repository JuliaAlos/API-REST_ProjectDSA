package edu.upc.dsa.services;

import edu.upc.dsa.PlanesManager;
import edu.upc.dsa.PlanesManagerDAOImpl;
import edu.upc.dsa.models.*;
import edu.upc.dsa.transferObjects.InsigniaTO;
import edu.upc.dsa.transferObjects.PlanePlayerTO;
import edu.upc.dsa.transferObjects.PlaneTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/planes", description = "Endpoint to the Planes Service")
@Path("/planes")
public class PlanesService {

    private PlanesManager manager;

    public PlanesService() {
        this.manager = PlanesManagerDAOImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get all planes in the system.", notes = "asdasd", response = PlaneModel.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No planes in the system yet.")
    })
    @Path("/GetAllPlanes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlanes() {
        List<PlaneModel> listPlane = this.manager.getAll();
        GenericEntity<List<PlaneModel>> entity = new GenericEntity<List<PlaneModel>>(listPlane){};
        if (listPlane.size() == 0){
            return Response.status(404).build();
        }
        else{
            return Response.status(200).entity(entity).build();
        }
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get plane by its model.", notes = "asdasd", response = PlaneModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found (plane not found)")
    })
    @Path("/getPlaneByModel/{planeModel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaneByModel(@PathParam("planeModel") String planeModel) {
        if (this.manager.existPlaneModel(planeModel)) {
            PlaneModel plane = this.manager.getPlaneByModel(planeModel);
            GenericEntity<PlaneModel> entity = new GenericEntity<PlaneModel>(plane){};
            return Response.status(200).entity(entity).build();
        }
        else{
            return Response.status(404).build();
        }
    }

    //------------------------------

    @POST
    @ApiOperation(value = "Add a plane to a player", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added"),
            @ApiResponse(code = 409, message = "No plane or player found in the system.")

    })

    @Path("/addPlaneToPlayer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaneToPlayer(PlanePlayerTO planePlayerTO) {
        if (this.manager.existPlaneModel(planePlayerTO.getPlaneModel()) && (this.manager.existPlayer(planePlayerTO.getPlayerName()))){
            this.manager.addPlaneToPlayer(planePlayerTO.getPlaneModel(),planePlayerTO.getPlayerName());
            return Response.status(201).build();
        }
        else{
            return Response.status(409).build();
        }
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get the list of planes of a player.", notes = "asdasd", response = PlaneTO.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The player has no planes yet.")
    })
    @Path("/getListPlanesPlayer/{playername}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListsPlanesPlayer (@PathParam("playername") String playername) {

        List <Plane> listPlane = this.manager.getAllFromPlayer(playername);
        List<PlaneTO> listPlanePlayer = new LinkedList<>();
        listPlane.forEach(plane -> listPlanePlayer.add(new PlaneTO(plane)));

        GenericEntity<List<PlaneTO>> entity = new GenericEntity<List<PlaneTO>>(listPlanePlayer){};
        if (listPlanePlayer.size() == 0){
            return Response.status(404).build();
        }
        else{
            return Response.status(200).entity(entity).build();
        }
    }



}
