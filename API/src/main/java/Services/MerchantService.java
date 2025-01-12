
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
    public Merchant createMerchant(Merchant merchant) {
        Merchants.put(merchant.getId(), merchant);

        return merchant;
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
    // Static getter to access merchants map
    public static HashMap<UUID, Merchant> getMerchants() {
        return Merchants;
    }
}