package services.interfaces;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import models.Customer;

import java.util.UUID;

public interface CustomerServiceClient {
    @POST
    @Path("customer")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Customer postCustomer(String name);

    @GET
    @Path("customer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer getCustomer(UUID id);
}
