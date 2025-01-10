package Services.Interfaces;

import models.Merchant;
import java.util.UUID;

public interface IMerchantService {
    Merchant createMerchant(String name, String bankAccountNumber); 
    Merchant getMerchantById(UUID id);
    boolean deleteMerchant(UUID id);
}
