package services;

import Exceptions.UserException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import models.Customer;
import models.Merchant;
import models.dtos.UserRequestDto;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import services.interfaces.MerchantServiceClient;

import java.util.UUID;

public class MerchantService {
    ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
    ResteasyWebTarget baseURL = client.target("http://localhost:8080");
    MerchantServiceClient service = baseURL.proxy(MerchantServiceClient.class);

    public Merchant createMerchant(UserRequestDto user){
        Response response =  service.postMerchant(user);

        return response.readEntity(Merchant.class);
    }

    public Merchant getMerchantById(UUID id) throws UserException {
        Response response =  service.getMerchantById(id);

        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()){
            throw new UserException(response.readEntity(String.class));
        }

        return response.readEntity(Merchant.class);
    }

    public Merchant getMerchantByName(String name) throws UserException {
        Response response =  service.getMerchantByName(name);

        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()){
            throw new UserException(response.readEntity(String.class));
        }

        return response.readEntity(Merchant.class);
    }
}
