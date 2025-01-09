package services.interfaces;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import models.Customer;
import models.Merchant;
import models.Payment;

import java.awt.*;
import java.util.UUID;

public interface CustomerServiceClient {
    @POST
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer postCustomer(String name);

    @GET
    @Path("customer")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(UUID id);
}
