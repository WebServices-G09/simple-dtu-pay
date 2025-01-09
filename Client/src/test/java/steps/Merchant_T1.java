package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Merchant;
import services.MerchantService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class Merchant_T1 {

    private UUID merchantId;
    private Merchant merchant;
    MerchantService merchantService = new MerchantService();

    @When("a merchant with name {string} registers")
    public void a_merchant_with_name_registers(String string) {
        merchantId = merchantService.createMerchant(string).getId();
    }

    @Then("a Merchant with name {string} has been created")
    public void it_returns_the_merchant_object_with_name(String string) {
        merchant = merchantService.getMerchantById(merchantId);
        assertEquals(merchant.getName(), string);
    }

    @When("I call the getMerchant service with name {string}")
    public void iCallTheGetMerchantServiceWithName(String string) {
        merchant = merchantService.getMerchantByName(string);
    }
    @Then("I get the Merchant with name {string}")
    public void iGetTheMerchantWithName(String string) {
        assertEquals(merchant.getName(), string);
    }

}
