package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerDAOImpl;
import edu.upc.dsa.PlanesManager;
import edu.upc.dsa.PlanesManagerDAOImpl;
import edu.upc.dsa.models.*;
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
    private GameManager managerUser;

    public PlanesService() {
        this.manager = PlanesManagerDAOImpl.getInstance();
        this.managerUser = GameManagerDAOImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get all planes in the system.", notes = "asdasd", response = PlaneModel.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No planes in the system yet.")
    })
    @Path("/GetAllPlanes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlanes() {
        List<PlaneModel> listPlane = this.manager.getAll();
        GenericEntity<List<PlaneModel>> entity = new GenericEntity<List<PlaneModel>>(listPlane) {
        };
        if (listPlane.size() == 0) {
            return Response.status(404).build();
        } else {
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
            GenericEntity<PlaneModel> entity = new GenericEntity<PlaneModel>(plane) {
            };
            return Response.status(200).entity(entity).build();
        } else {
            return Response.status(404).build();
        }
    }

    //------------------------------

    @POST
    @ApiOperation(value = "Add a plane to a player", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added"),
            @ApiResponse(code = 409, message = "No plane or player found in the system or not enough bitcoins.")

    })

    @Path("/addPlaneToPlayer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaneToPlayer(PlanePlayerTO planePlayerTO) {
        if (this.manager.existPlaneModel(planePlayerTO.getPlaneModel()) && (this.manager.existPlayer(planePlayerTO.getPlayerName()))) {
            int pricePlane = this.manager.getPlaneByModel(planePlayerTO.getPlaneModel()).getPrice();
            int bitcoinsUser = this.managerUser.getUser(planePlayerTO.getPlayerName()).getPlayer().getBitcoins();
            if (pricePlane <= bitcoinsUser) {
                this.manager.addPlaneToPlayer(planePlayerTO.getPlaneModel(), planePlayerTO.getPlayerName());
                this.managerUser.setMoney(bitcoinsUser - pricePlane, planePlayerTO.getPlayerName());
                return Response.status(201).build();
            }
        }
        return Response.status(409).build();
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get the list of planes of a player.", notes = "asdasd", response = PlaneTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The player has no planes yet.")
    })
    @Path("/getListPlanesPlayer/{playername}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListsPlanesPlayer(@PathParam("playername") String playername) {

        List<Plane> listPlane = this.manager.getAllFromPlayer(playername);
        List<PlaneTO> listPlanePlayer = new LinkedList<>();
        listPlane.forEach(plane -> listPlanePlayer.add(new PlaneTO(plane)));

        GenericEntity<List<PlaneTO>> entity = new GenericEntity<List<PlaneTO>>(listPlanePlayer) {
        };
        if (listPlanePlayer.size() == 0) {
            return Response.status(404).build();
        } else {
            return Response.status(200).entity(entity).build();
        }
    }

    //------------------------------

    @POST
    @ApiOperation(value = "Add an upgrade to a plane of a player", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Upgrade added"),
            @ApiResponse(code = 409, message = "No plane or player found in the system, or not enough bitcoins.")

    })

    @Path("/addUpgradeToPlayer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUpgradeToPlayer(Upgrade upgrade) {
        User user = managerUser.getUser(upgrade.getPlayerName());
        Integer currentBitcoins = user.getPlayer().getBitcoins();
        int priceUpdate = 10;
        int actualizedBitcoins = currentBitcoins - priceUpdate;
        if (actualizedBitcoins >= 0) {
            if (this.manager.existPlaneModel(upgrade.getPlaneModelModel()) && (this.manager.existPlayer(upgrade.getPlayerName()))) {
                this.manager.addUpgradeToPlayer(upgrade.getModificationCode(), upgrade.getPlayerName(), upgrade.getPlaneModelModel());
                this.managerUser.setMoney(actualizedBitcoins, user.getUserName());
                return Response.status(201).build();
            } else {
                return Response.status(409).build();
            }
        } else {
            return Response.status(409).build();
        }
    }

    //------------------------------

    @GET
    @ApiOperation(value = "Get the list of upgrades of the planes of a player.", notes = "asdasd", response = Upgrade.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The player has no upgrades yet.")
    })

    @Path("/getAllUpgradesFromPlayer/{playername}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUpgradesFromPlayer(@PathParam("playername") String playername) {
        List<Upgrade> listUpgrades = this.manager.getAllUpgradesFromPlayer(playername);
        GenericEntity<List<Upgrade>> entity = new GenericEntity<List<Upgrade>>(listUpgrades) {
        };

        if (listUpgrades.size() == 0) {
            return Response.status(404).build();
        } else {
            return Response.status(200).entity(entity).build();
        }
    }


    @GET
    @ApiOperation(value = "Get plane of an users.", notes = "asdasd", response = Plane.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/getPlaneByModel/{userName}/{planeModel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaneByModelUser(@PathParam("userName") String userName, @PathParam("planeModel") String planeModel) {
        if (this.manager.existPlaneModel(planeModel) && this.managerUser.existUser(userName)) {
            List<Plane> listPlane = this.manager.getAllFromPlayer(userName);
            for (Plane planeUser : listPlane) {
                if (planeUser.getPlaneModelModel().equals(planeModel)) {
                    PlaneTO plane = new PlaneTO(planeUser);
                    List<Upgrade> listUpgrades = this.manager.getAllUpgradesFromPlayer(userName);
                    for (Upgrade upgrade : listUpgrades) {
                        if (upgrade.getPlaneModelModel().equals(planeModel)) {
                            if (upgrade.getModificationCode().equals("0") && (plane.getEnginesLife() < plane.getMaxEnginesLife())) {
                                plane.setEnginesLife(plane.getEnginesLife() + 10);
                            } else if (upgrade.getModificationCode().equals("1") && (plane.getVelY() < plane.getMaxManoeuvrability())) {
                                plane.setVelY(plane.getVelY() + 10);
                            } else if (upgrade.getModificationCode().equals("2") && (plane.getVelX() < plane.getMaxSpeed())) {
                                plane.setVelX(plane.getVelX() + 10);
                            } else if (upgrade.getModificationCode().equals("3") && (plane.getFuel() > plane.getMinFuel())) {
                                plane.setFuel(plane.getFuel() - 10);
                            } else if (upgrade.getModificationCode().equals("4") && (plane.getGravity() > plane.getMinWeight())) {
                                plane.setGravity(plane.getGravity() - 10);
                            }
                        }
                    }
                    return Response.status(200).entity(plane).build();
                }
            }
        }
        return Response.status(404).build();
    }



}
