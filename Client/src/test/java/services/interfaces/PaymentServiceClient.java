package services.interfaces;

import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Customer;
import models.Merchant;
import models.Payment;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

@Path("payment")
public interface PaymentServiceClient
{
	@GET
	@Path("list/{customerId}/{merchantId}")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listPayments(@PathParam("customerId") UUID customerId, @PathParam("merchantId") UUID merchantId);
	
    @POST
    @Path("initialize")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postInitializePayment(String inputJson);

    @POST
    @Path("pay")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postPay(String inputJson);
}
