
package Controllers;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import Services.MerchantService;
import models.Merchant;

import java.util.UUID;

@Path("/merchant")
public class MerchantController
{
    private static MerchantService merchantService;

    public MerchantController()
    {
        merchantService = new MerchantService();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMerchantById(@PathParam("id") UUID id) {
        try {
            Merchant merchant = merchantService.getMerchantById(id);

            if (merchant == null || merchant.getId() == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Merchant does not exist\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.status(Response.Status.OK)
                    .entity(merchant)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"An unexpected error occurred\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerMerchant(String inputJson) {
        try {
            var jsonObject = new com.fasterxml.jackson.databind.ObjectMapper().readTree(inputJson);
            String name = jsonObject.get("name").asText();

            var newMerchant = merchantService.createMerchant(name);

            if (newMerchant.getId() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Merchant creation failed\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.status(Response.Status.OK)
                    .entity(newMerchant)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Invalid input format\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMerchant(@PathParam("id") UUID id) {
        try {
            boolean isDeleted = merchantService.deleteMerchant(id);

            if (!isDeleted) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Merchant does not exist\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.status(Response.Status.OK)
                    .entity("{\"message\": \"Merchant deleted successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"An unexpected error occurred\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}