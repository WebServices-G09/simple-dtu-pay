package services;

import Exceptions.UserException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import models.Customer;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import services.interfaces.CustomerServiceClient;

import java.text.ParseException;
import java.util.UUID;
public class CustomerService {
    ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
    ResteasyWebTarget baseURL = client.target("http://localhost:8080");
    CustomerServiceClient service = baseURL.proxy(CustomerServiceClient.class);

    public Customer createCustomer(String fName, String lName, int cpr, int bankNumber){
          Response response = service.postCustomer(fName, lName, cpr, bankNumber);

          return response.readEntity(Customer.class);
    }

    public Customer getCustomerById(UUID id) throws UserException {
        Response response = service.getCustomerById(id);

        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()){
            throw new UserException(response.readEntity(String.class));
        }
        return response.readEntity(Customer.class);
    }



}
