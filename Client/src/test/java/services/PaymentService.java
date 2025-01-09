package services;

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

public class PaymentService {

    ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
    ResteasyWebTarget baseURL = client.target("http://localhost:8080");
    PaymentServiceClient service = baseURL.proxy(PaymentServiceClient.class);

    public Customer registerCustomer(String name){
        return service.postCustomer(name);
    }

    public Merchant registerMerchant(String name){
        return service.postMerchant(name);
    }


}