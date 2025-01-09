package Services.Interfaces;

import Exceptions.UserException;
import models.Merchant;

import java.util.UUID;

public interface IMerchantService {
    public Merchant getMerchant(String name);
    public Merchant createMerchant(String name);
    public Merchant getMerchantById(UUID id) throws UserException;
    public Merchant getMerchantByName(String name) throws UserException;
    public boolean deleteMerchant(UUID id);
}