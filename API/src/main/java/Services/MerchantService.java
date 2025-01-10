
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
    public Merchant getMerchant(String name) {
        for (Merchant merchant : merchants.values()) {
            if (merchant.getName().equalsIgnoreCase(name)) {
                return merchant;
            }
        }

        return null;
    }

    @Override
    public Merchant getMerchantByName(String name) throws UserException {
        var merchant = getMerchant(name);

        if (merchant == null) {
            throw new UserException("{\"error\": \"Merchant does not exist\"}");
        }

        return merchant;
    }

    @Override
    public Merchant getMerchantById(UUID id) throws UserException {
        var merchant = merchants.get(id);

        if (merchant == null) {
            throw new UserException("{\"error\": \"Merchant does not exist\"}");
        }

        return merchant;
    }

    @Override
    public boolean deleteMerchant(UUID id) {
        if(merchants.containsKey(id)){
            merchants.remove(id);
            return true;
        }

        return false;
    }
}