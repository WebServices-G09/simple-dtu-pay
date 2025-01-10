
package Services;

import Exceptions.UserException;
import Services.Interfaces.IMerchantService;
import models.Merchant;
import models.Merchant;
import models.Payment;

import java.util.HashMap;
import java.util.UUID;
public class MerchantService implements IMerchantService {
    private static HashMap<UUID, Merchant> Merchants = new HashMap<>();

    public MerchantService(){}

    @Override
    public Merchant createMerchant(String fName, String lName, int cpr, int bankNumber) {
        var Merchant = new Merchant(fName, lName, cpr, bankNumber);
        Merchants.put(Merchant.getId(), Merchant);

        return Merchant;
    }




    @Override
    public Merchant getMerchantById(UUID id) throws UserException {
        var Merchant = Merchants.get(id);

        if (Merchant == null) {
            throw new UserException("{\"error\": \"Merchant does not exist\"}");
        }

        return Merchant;
    }

    @Override
    public boolean deleteMerchant(UUID id) {
        if(Merchants.containsKey(id)){
            Merchants.remove(id);
            return true;
        }

        return false;
    }
}