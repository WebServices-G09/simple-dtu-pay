
package Controllers;
import Exceptions.UserException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import Services.CustomerService;
import models.Customer;
import models.dto.CustomerRequestDto;

import java.util.UUID;

@Path("/customer")
public class CustomerController
{
    private static CustomerService customerService;

    public CustomerController()
    {
        customerService = new CustomerService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(CustomerRequestDto customerRequest) {
        try {
            var newCustomer = customerService.createCustomer(customerRequest);

            return Response.status(Response.Status.OK)
                    .entity(newCustomer)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Customer creation failed\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") UUID id) {
        try {
            Customer customer = customerService.getCustomerById(id);

            return Response.status(Response.Status.OK)
                    .entity(customer)
                    .build();
        } catch (UserException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id") UUID id) {
        try {
            boolean isDeleted = customerService.deleteCustomer(id);

            return Response.status(Response.Status.OK)
                    .entity(isDeleted)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (UserException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}