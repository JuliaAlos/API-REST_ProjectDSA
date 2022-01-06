package edu.upc.dsa.services;

import edu.upc.dsa.InsigniasManager;
import edu.upc.dsa.InsigniasManagerDAOImpl;
import edu.upc.dsa.models.Insignia;
import edu.upc.dsa.models.InsigniaModel;
import edu.upc.dsa.transferObjects.InsigniaPlayerTO;
import edu.upc.dsa.transferObjects.InsigniaTO;
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

@Api(value = "/insignias", description = "Endpoint to the Insignias Service")
@Path("/insignias")
public class InsigniasService {
    private InsigniasManager manager;

    public InsigniasService() {
        this.manager = InsigniasManagerDAOImpl.getInstance();
        }

   //------------------------------

    @GET
    @ApiOperation(value = "Get all insignias in the system.", notes = "asdasd")
    @ApiResponses(value = {
                @ApiResponse(code = 200, message = "OK", response = InsigniaModel.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "No insignias in the system yet.")
    })
    @Path("/GetAllInsignias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInsignias() {
        List<InsigniaModel> listInsignia = this.manager.getAll();
        GenericEntity<List<InsigniaModel>> entity = new GenericEntity<List<InsigniaModel>>(listInsignia){};
        if (listInsignia.size() == 0){
            return Response.status(404).build();
        }
        else{
            return Response.status(200).entity(entity).build();
        }
    }

    //------------------------------

    @POST
    @ApiOperation(value = "Add an insignia to a player", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added"),
            @ApiResponse(code = 409, message = "No insignia or player found in the system.")

    })

    @Path("/addInsigniaToPlayer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInsigniaToPlayer(InsigniaPlayerTO insigniaPlayerTO) {


        if (this.manager.existInsigniaModel(insigniaPlayerTO.getInsigniaName()) && (this.manager.existPlayer(insigniaPlayerTO.getPlayerName()))){
            this.manager.addInsigniaToPlayer(insigniaPlayerTO.getInsigniaName(),insigniaPlayerTO.getPlayerName());
            return Response.status(201).build();
        }
        else{
            return Response.status(409).build();
        }
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get the list of insignias of a player.", notes = "asdasd", response = InsigniaTO.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The player has no insignias yet.")
    })
    @Path("/getListInsigniasPlayer/{playername}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListInsigniasPlayer(@PathParam("playername") String playername) {
        List <Insignia> listInsignia = this.manager.getAllFromPlayer(playername);
        List<InsigniaTO> listInsigniaPlayer = new LinkedList<>();
        listInsignia.forEach(insignia -> listInsigniaPlayer.add(new InsigniaTO(insignia)));

        GenericEntity<List<InsigniaTO>> entity = new GenericEntity<List<InsigniaTO>>(listInsigniaPlayer){};
        if (listInsigniaPlayer.size() == 0){
            return Response.status(404).build();
        }
        else{
            return Response.status(200).entity(entity).build();
        }
    }

}
