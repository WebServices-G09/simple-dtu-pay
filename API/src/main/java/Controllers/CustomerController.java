
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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") UUID id) {
        try {
            Customer customer = customerService.getCustomerById(id);

            if (customer == null || customer.getId() == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Customer does not exist\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.status(Response.Status.OK)
                    .entity(customer)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"An unexpected error occurred\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(String inputJson) {
        try {
            var jsonObject = new com.fasterxml.jackson.databind.ObjectMapper().readTree(inputJson);
            String name = jsonObject.get("name").asText();

            var newCustomer = customerService.createCustomer(name);

            if (newCustomer.getId() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Customer creation failed\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.status(Response.Status.OK)
                    .entity(newCustomer)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Invalid input format\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

}