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

public class PaymentService {

    Client client = ClientBuilder.newClient();

    WebTarget baseURL=
            client.target("http://localhost:8080/");

    public Customer getCustomer(){

        return baseURL.path("customer").request().get(Customer.class);
    }

    public void setCustomer(Customer customer) {
        Response response = baseURL.path("customer")
                .request()
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON));

        System.out.println("Post response: " + response.getStatus());

        if (response.getStatus() != 200) {
            System.err.println("Error creating customer: " + response.readEntity(String.class));
        }
    }



}