
package Controllers;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import Services.CustomerService;
import models.Customer;

import java.util.UUID;

@Path("/customer")
public class CustomerController
{
    private static CustomerService customerService;

    public CustomerController()
    {
        customerService = new CustomerService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(UUID id)
    {
        Customer customer = customerService.getCustomerById(id);

        if(customer.getId() == null){
            Response.status(
                    Response.Status.BAD_REQUEST)
                    .entity("Customer does not exist").build();
        }
        //.entity(Entity.entity(object, MediaType.APPLICATION_JSON_TYPE)
        return Response.ok(Entity.entity(customer, MediaType.APPLICATION_JSON)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(String name)
    {
        var newCustomer = new Customer();
        newCustomer = customerService.createCustomer(name);

        if(newCustomer.getId() == null){
            Response.status(
                            Response.Status.BAD_REQUEST)
                    .entity("Customer creation failed").build();
        }

        return Response.ok(Entity.entity(newCustomer, MediaType.APPLICATION_JSON)).build();
    }
}