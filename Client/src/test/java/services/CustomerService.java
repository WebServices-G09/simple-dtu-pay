package services;

import jakarta.ws.rs.client.ClientBuilder;
import models.Customer;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import services.interfaces.CustomerServiceClient;

import java.util.UUID;
public class CustomerService {
    ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
    ResteasyWebTarget baseURL = client.target("http://localhost:8080");
    CustomerServiceClient service = baseURL.proxy(CustomerServiceClient.class);

    public Customer createCustomer(String name){
          return service.postCustomer(name);
    }

    public Customer getCustomerById(UUID id){
        return service.getCustomerById(id);
    }

    public Customer getCustomerByName(String name){
        return service.getCustomerByName(name);
    }


}
