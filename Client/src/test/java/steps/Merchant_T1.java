package steps;

import Exceptions.UserException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Merchant;
import models.dtos.UserRequestDto;
import services.MerchantService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class Merchant_T1 {

    private UUID merchantId;
    private Merchant merchant;
    MerchantService merchantService = new MerchantService();

    @When("a merchant with fname {string} lName {string} cpr {string} accountNum {string} registers")
    public void a_merchant_with_fname_l_name_cpr_account_num_registers(String fName, String lName, String cpr, String accountNum) {
        var userDto = new UserRequestDto();
        userDto.setFirstName(fName);
        userDto.setLastName(lName);
        userDto.setCpr(cpr);
        userDto.setBankAccountNumber(accountNum);

        merchantId = merchantService.createMerchant(userDto).getId();
    }
    @Then("a merchant with name {string} {string} has been created")
    public void a_merchant_with_name_has_been_created(String fName, String lName) throws UserException {
        merchant = merchantService.getMerchantById(merchantId);
        assertEquals(merchant.getFirstName(), fName);
        assertEquals(merchant.getLastName(), lName);
    }
}
