package services;

import jakarta.ws.rs.client.ClientBuilder;
import models.Merchant;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import services.interfaces.MerchantServiceClient;

import java.util.UUID;

public class MerchantService {
    ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
    ResteasyWebTarget baseURL = client.target("http://localhost:8080");
    MerchantServiceClient service = baseURL.proxy(MerchantServiceClient.class);

    public Merchant createMerchant(String name){
        return service.postMerchant(name);
    }

    public Merchant getMerchantById(UUID id){
        return service.getMerchantById(id);
    }

    public Merchant getMerchantByName(String name){
        return service.getMerchantByName(name);
    }
}
