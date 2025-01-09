package services.interfaces;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import models.Merchant;

import java.util.UUID;

@Path("merchant")
public interface MerchantServiceClient {
    @POST

    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Merchant postMerchant(String name);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Merchant getMerchant(UUID id);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Merchant getMerchantById(@PathParam("id") UUID id);

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Merchant getMerchantByName(@PathParam("name") String name);
}
