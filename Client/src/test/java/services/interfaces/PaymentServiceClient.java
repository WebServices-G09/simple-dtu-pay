package services.interfaces;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import models.Customer;
import models.Merchant;
import models.Payment;

import java.awt.*;
import java.util.UUID;

public interface PaymentServiceClient
{
    @GET
    @Path("person")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer();

    @POST
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer postCustomer(String name);

    @POST
    @Path("merchant")
    @Consumes(MediaType.APPLICATION_JSON)
    public Merchant postMerchant(String name);

    @POST
    @Path("payment")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean postPayment(double amount, UUID customerId, UUID merchantId);
}
