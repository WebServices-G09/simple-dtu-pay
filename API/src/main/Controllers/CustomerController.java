
package Controllers;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import Services.CustomerService;

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
    public void getCustomerById(UUID id)
    {
        Customer customer = customerService.getCustomerById(id);

        if(customer.getId() == null){
            Response.status(
                    Response.Status.BAD_REQUEST)
                    .entity(e.toString()).build();
        }
        //.entity(Entity.entity(object, MediaType.APPLICATION_JSON_TYPE)
        return Response.ok(Entity.entity(customer, MediaType.APPLICATION_JSON)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putPerson(Person person)
    {
        service.putPerson(person);
        //Response.status(Response.Status.BAD_REQUEST).entity(e.toString()).build();
    }
}