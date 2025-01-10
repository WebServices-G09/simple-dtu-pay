package services.interfaces;

import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Customer;
import models.Merchant;
import models.Payment;

import java.awt.*;
import java.util.UUID;

public interface PaymentServiceClient
{
	@GET
	@Path("payment/list")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listPayments();
	
    @POST
    @Path("payment/initialize")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postInitializePayment(String inputJson);

    @POST
    @Path("payment/pay")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postPay(String inputJson);
}
