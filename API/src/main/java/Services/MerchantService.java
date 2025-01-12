package Services;

import Exceptions.UserException;
import Services.Interfaces.IMerchantService;
import models.Merchant;
import models.dto.MerchantRequestDto;

import java.util.HashMap;
import java.util.UUID;

public class MerchantService implements IMerchantService {
    private static HashMap<UUID, Merchant> Merchants = new HashMap<>();

    public MerchantService(){}

    @Override
    public Merchant createMerchant(MerchantRequestDto merchantRequestDto) {
        var merchant = new Merchant(
                merchantRequestDto.getFirstName(),
                merchantRequestDto.getLastName(),
                merchantRequestDto.getCpr(),
                merchantRequestDto.getBankAccountNumber()
        );

        Merchants.put(merchant.getId(), merchant);

        return merchant;
    }

    @Override
    public Merchant getMerchantById(UUID id) throws UserException {
        var Merchant = Merchants.get(id);

        if (Merchant == null) {
            String error = String.format("merchant with id \"%s\" is unknown", id);
            throw new UserException(error);
        }

        return Merchant;
    }

    @Override
    public boolean deleteMerchant(UUID id) throws UserException {
        if(!Merchants.containsKey(id)){
            String error = String.format("merchant with id \"%s\" is unknown", id);
            throw new UserException(error);
        }

        Merchants.remove(id);
        return true;
    }

    // Static getter to access merchants map
    public static HashMap<UUID, Merchant> getMerchants() {
        return Merchants;
    }
}