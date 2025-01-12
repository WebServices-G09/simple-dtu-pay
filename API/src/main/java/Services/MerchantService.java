
package Services;

import Exceptions.UserException;
import Services.Interfaces.IMerchantService;
import models.Merchant;

import java.util.HashMap;
import java.util.UUID;

public class MerchantService implements IMerchantService {
    private static HashMap<UUID, Merchant> merchants = new HashMap<>();

    public MerchantService(){
    }

    @Override
    public Merchant createMerchant(String name){
        var merchant = new Merchant(name);
        merchants.put(merchant.getId(), merchant);

        return merchant;
    }

    @Override
    public Merchant getMerchantById(UUID id) throws UserException {
        var merchant = merchants.get(id);

        if (merchant == null) {
            String error = String.format("merchant with id \"%s\" is unknown", id);
            throw new UserException(error);
        }

        return merchant;
    }

    @Override
    public boolean deleteMerchant(UUID id) throws UserException {
        if(!merchants.containsKey(id)){
            String error = String.format("merchant with id \"%s\" is unknown", id);
            throw new UserException(error);
        }

        merchants.remove(id);
        return true;
    }
}