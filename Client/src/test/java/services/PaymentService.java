package services;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import models.Payment;
import models.PaymentStatus;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import services.interfaces.PaymentServiceClient;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

import Exceptions.UserException;

public class PaymentService
{

    ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
    ResteasyWebTarget baseURL = client.target("http://localhost:8080");
    PaymentServiceClient service = baseURL.proxy(PaymentServiceClient.class);

    public UUID initializePayment(UUID customerId, UUID merchantId, double amount) throws UserException
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

            if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                throw new UserException(response.readEntity(String.class));
            }

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
    
    public ArrayList<Payment> listPayments(UUID customerId, UUID merchantId) {
        Response response = service.listPayments(customerId, merchantId);

        var list =  response.readEntity(new GenericType<ArrayList>() {})
                .stream()
                .map((Function<HashMap<String,Object>, Payment>) h -> new Payment(
                        UUID.fromString((String) h.get("id")),
                        UUID.fromString((String) h.get("customerId")),
                        UUID.fromString((String) h.get("merchantId")),
                        ((BigDecimal) h.get("amount")).intValue(),
                        PaymentStatus.valueOf((String) h.get("status")))).toList();

        return new ArrayList<Payment>(list);
    }
}