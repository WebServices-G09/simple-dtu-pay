package Services.Interfaces;

import Exceptions.UserException;
import models.Merchant;
import models.dto.MerchantRequestDto;

import java.util.UUID;

public interface IMerchantService {

    public Merchant createMerchant(MerchantRequestDto merchant);
    public Merchant getMerchantById(UUID id) throws UserException;
    public boolean deleteMerchant(UUID id) throws UserException;
}