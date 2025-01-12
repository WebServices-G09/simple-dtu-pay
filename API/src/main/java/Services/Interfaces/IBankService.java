package Services.Interfaces;

import java.math.BigDecimal;

public interface IBankService {
    public void transferMoney(String debtorAccountId, String creditorAccountId, BigDecimal amount, String description);
}
