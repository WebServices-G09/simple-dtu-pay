package Services.Interfaces;

import Exceptions.UserException;
import models.Merchant;

import java.util.UUID;

public interface IMerchantService {

    public Merchant createMerchant(String fName, String lName, int cpr, int bankNumber);
    public Merchant getMerchantById(UUID id) throws UserException;
    public boolean deleteMerchant(UUID id);
}