package controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import services.DtuPayService;

@Path("/payment")
public class DtuPayController {
    private DtuPayService dtuPayService = new DtuPayService();
    @GET
    @Path("/status/amount/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPaymentStatus(@PathParam("amount") double amount) {
        String customerId = "123";
        var response = dtuPayService.processPayment(customerId, amount);

        return "{ \"status\": \"" + response + "\" }";
    }
}
