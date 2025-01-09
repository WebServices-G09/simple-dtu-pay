
package Services;

import Services.Interfaces.ICustomerService;
import Services.Interfaces.IMerchantService;
import models.Merchant;

import java.util.HashMap;
import java.util.UUID;

public class MerchantService implements IMerchantService {
    HashMap<UUID, Merchant> merchants = new HashMap<>();
    public MerchantService(){}

    @Override
    public Merchant createMerchant(String name){
        var merchant = new Merchant(name);
        merchants.put(merchant.getId(), merchant);

        return merchant;
    }
    @Override
    public Merchant getMerchantById(UUID id) {
        return merchants.get(id);
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