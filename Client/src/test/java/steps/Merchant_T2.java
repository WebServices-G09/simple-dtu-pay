package steps;

import Exceptions.UserException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Merchant;
import services.MerchantService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class Merchant_T1 {

    private Merchant merchant;
    MerchantService merchantService = new MerchantService();

    @Given("an existing merchant with Id {string}")
    public void anExistingMerchantWithId(UUID string) throws UserException {
    	merchant = merchantService.getMerchantById(string);
    }
    @When("a merchant with Id {string} is unregistered")
    public void aMerchantWithIdIsUnregistered(UUID string) {
        deleteMerchant(string)
    }
    @Then("a merchant with Id {string} no longer exists in the merchant list")
    public void aMerchantWithIdNoLongerExistsInTheMerchantList(UUID string) throws UserException {
    	UserException exception = assertThrows(UserException.class, () ->
    	merchantService.getMerchantById(string));

    	assertEquals("404 not found", exception.getMessage());
    }

}