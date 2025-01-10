package services;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Customer;
import models.Merchant;
import models.Payment;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import services.interfaces.PaymentServiceClient;

import java.util.UUID;

public class PaymentService
{

    ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
    ResteasyWebTarget baseURL = client.target("http://localhost:8080");
    PaymentServiceClient service = baseURL.proxy(PaymentServiceClient.class);

    public UUID initializePayment(UUID customerId, UUID merchantId, double amount)
    {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode obj = mapper.createObjectNode();

        obj.put("customerId", customerId.toString());
        obj.put("merchantId", merchantId.toString());
        obj.put("amount", amount);

        try
        {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            var response = service.postInitializePayment(jsonString);

            return response.readEntity(UUID.class);
        }
        catch (JsonProcessingException e)
        {
            return null;
        }
    }

    public boolean pay(UUID paymentId, double amount)
    {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode obj = mapper.createObjectNode();

        obj.put("paymentId", paymentId.toString());
        obj.put("amount", amount);

        try
        {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            Response response = service.postPay(jsonString);

            if(response.hasEntity())
            {
                System.out.println(response.readEntity(String.class));
            }

            return response.getStatus() == Response.Status.OK.getStatusCode();
        }
        catch (JsonProcessingException e)
        {
            return false;
        }
    }
}