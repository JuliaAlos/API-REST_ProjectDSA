package edu.upc.dsa.services;

import edu.upc.dsa.ForumManager;
import edu.upc.dsa.ForumManagerDAOImpl;
import edu.upc.dsa.PlanesManager;
import edu.upc.dsa.PlanesManagerDAOImpl;
import edu.upc.dsa.models.*;
import edu.upc.dsa.transferObjects.PlanePlayerTO;
import edu.upc.dsa.transferObjects.PlaneTO;
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
import java.util.LinkedList;
import java.util.List;

@Api(value = "/forum", description = "Endpoint to the Forum Service")
@Path("/forum")
public class ForumService {

    private ForumManager manager;

    public ForumService() {
        this.manager = ForumManagerDAOImpl.getInstance();
    }


    @POST
    @ApiOperation(value = "Add user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create")

    })

    @Path("/addEntry")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEntry (ForumEntry entry) {
        this.manager.addEntry(entry.getUserName(), entry.getMessage());
        return Response.status(201).build();
    }


    //---------------------------


    @GET
    @ApiOperation(value = "Get all entries of the forum in the system.", notes = "asdasd", response = ForumEntry.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No planes in the system yet.")
    })
    @Path("/GetAllEntries")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEntries() {
        List<ForumEntry> listEntries = this.manager.getAllEntries();
        GenericEntity<List<ForumEntry>> entity = new GenericEntity<List<ForumEntry>>(listEntries){};
        if (listEntries.size() == 0){
            return Response.status(404).build();
        }
        else{
            return Response.status(200).entity(entity).build();
        }
    }




}
