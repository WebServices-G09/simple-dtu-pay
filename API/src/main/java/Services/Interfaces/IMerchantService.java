package Services.Interfaces;

import models.Merchant;

import java.util.UUID;

public interface IMerchantService {
    public Merchant createMerchant(String name);
    public Merchant getMerchantById(UUID id);
    public boolean deleteMerchant(UUID id);
}