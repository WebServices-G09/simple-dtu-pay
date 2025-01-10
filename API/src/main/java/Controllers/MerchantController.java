
package Controllers;
import Exceptions.UserException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import Services.MerchantService;
import models.Customer;
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

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerMerchant(String firstName, String lastName, int cpr, int bankAccountNumber) {
        try {
            var newMerchant = merchantService.createMerchant(firstName, lastName, cpr, bankAccountNumber);

            return Response.status(Response.Status.OK)
                    .entity(newMerchant)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Merchant creation failed\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMerchantById(@PathParam("id") UUID id) {
        try {
            Merchant merchant = merchantService.getMerchantById(id);

            return Response.status(Response.Status.OK)
                    .entity(merchant)
                    .build();
        } catch (UserException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
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