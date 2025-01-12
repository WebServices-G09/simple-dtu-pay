package steps;

import Exceptions.UserException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Merchant;
import services.MerchantService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MerchantSteps {

    private UUID merchantId;
    private Merchant merchant;
    private String exception;
    MerchantService merchantService = new MerchantService();

    @When("a merchant with name {string} registers")
    public void a_merchant_with_name_registers(String string) {
        merchantId = merchantService.createMerchant(string).getId();
    }

    @Then("a Merchant with name {string} has been created")
    public void it_returns_the_merchant_object_with_name(String string) throws UserException {
        merchant = merchantService.getMerchantById(merchantId);
        assertEquals(merchant.getName(), string);
    }

    @When("the Merchant unregisters")
    public void theMerchantUnregisters() throws UserException {
        boolean success =  merchantService.unregiterMerchant(merchantId);
        assertTrue(success);
    }

    @Then("the Merchant is not registred")
    public void theMerchantIsNotRegistred() throws UserException {
        try {
            merchant = merchantService.getMerchantById(merchantId);
        } catch (UserException e) {
            exception = e.getMessage();
        }
    }

    @Then("the unkown merchant error message {string} is returned")
    public void theErrorMessageSIsUnknownIsReturned(String string) {
        String expectedError = String.format(string, merchantId);

        assertEquals(expectedError, exception);
    }
}
