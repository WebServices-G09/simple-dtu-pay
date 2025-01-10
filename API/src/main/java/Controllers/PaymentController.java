package Controllers;

import Exceptions.UserException;
import Services.CustomerService;
import Services.MerchantService;
import Services.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Payment;

import java.util.UUID;

@Path("/payment")
public class PaymentController
{
    private static PaymentService paymentService;
    private static CustomerService customerService;
    private static MerchantService merchantService;

    public PaymentController()
    {
        paymentService = new PaymentService();
        customerService = new CustomerService();
        merchantService = new MerchantService();
    }

    @POST
    @Path("initialize")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response initiatePayment(String inputJson)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonObject = objectMapper.readTree(inputJson);

            double amount = jsonObject.get("amount").asDouble();
            UUID customerId = UUID.fromString(jsonObject.get("customerId").asText());
            UUID merchantId = UUID.fromString(jsonObject.get("merchantId").asText());

            // check if they exist
            merchantService.getMerchantById(merchantId);
            customerService.getCustomerById(customerId);

            var payment = paymentService.initializePayment(customerId, merchantId, amount);
            return Response.status(Response.Status.OK)
                    .entity(payment.getId())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        catch (JsonProcessingException | UserException e)
        {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": "+ e.getMessage() + "}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @POST
    @Path("pay")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response pay(String inputJson)
    {
        try
        {
            var jsonObject = new com.fasterxml.jackson.databind.ObjectMapper().readTree(inputJson);
            UUID paymentId = UUID.fromString(jsonObject.get("paymentId").asText());
            double amount = jsonObject.get("amount").asDouble();

            boolean result = paymentService.pay(paymentId, amount);

            if(!result)
            {
                return Response.status(Response.Status.PAYMENT_REQUIRED)
                        .entity("{\"error\": \"The value sent does not match the requested amount\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        catch (JsonProcessingException e)
        {
            return Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
