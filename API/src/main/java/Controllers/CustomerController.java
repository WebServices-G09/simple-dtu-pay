
package Controllers;
import Exceptions.UserException;
import Services.BankServiceClient;
import dtu.ws.fastmoney.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import Services.CustomerService;
import models.Customer;

import java.math.BigDecimal;
import java.util.UUID;

@Path("/customer")
public class CustomerController
{
    private static CustomerService customerService;
    private static Account account;

    public CustomerController()
    {

        customerService = new CustomerService();
        account = new Account();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCustomer(String name) {
        try {
            var newCustomer = customerService.createCustomer(name);

            return Response.status(Response.Status.OK)
                    .entity( newCustomer)
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

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerByName(@PathParam("name") String name) {
        try {
            Customer customer = customerService.getCustomerByName(name);

            return Response.status(Response.Status.OK)
                    .entity(customer)
                    .type(MediaType.APPLICATION_JSON)
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

            if (!isDeleted) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Customer does not exist\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.status(Response.Status.OK)
                    .entity("{\"message\": \"Customer deleted successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"An unexpected error occurred\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @POST
    @Path("soap/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(String inputJson) {
        try {
            var jsonObject = new com.fasterxml.jackson.databind.ObjectMapper().readTree(inputJson);

            if (!jsonObject.has("firstName") || !jsonObject.has("lastName") ||
                    !jsonObject.has("cprNumber") || !jsonObject.has("initialBalance")) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Missing required fields: firstName, lastName, cprNumber, and initialBalance are required\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            String firstName = jsonObject.get("firstName").asText();
            String lastName = jsonObject.get("lastName").asText();
            String cprNumber = jsonObject.get("cprNumber").asText();
            BigDecimal initialBalance;

            try {
                initialBalance = new BigDecimal(jsonObject.get("initialBalance").asText());
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Invalid format for initialBalance. It must be a valid number.\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            var user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setCprNumber(cprNumber);

            BankService bankService = new BankServiceService().getBankServicePort();
            String accountId = bankService.createAccountWithBalance(user, initialBalance);

            var newCustomer = new Customer();
            newCustomer.setId(UUID.fromString(accountId));
            newCustomer.setName(firstName + " " + lastName);

            return Response.status(Response.Status.CREATED)
                    .entity(newCustomer)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Failed to create customer: " + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @GET
    @Path("soap/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") String accountId) {
        try {
            BankService bankService = new BankServiceService().getBankServicePort();

            Account account = bankService.getAccount(accountId);

            if (account == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Customer does not exist\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            Customer customer = new Customer();
            customer.setId(UUID.fromString(account.getId()));
            customer.setName(account.getUser().getFirstName() + " " + account.getUser().getLastName());

            return Response.status(Response.Status.OK)
                    .entity(customer)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (BankServiceException_Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Customer does not exist: " + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"An unexpected error occurred: " + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }


}