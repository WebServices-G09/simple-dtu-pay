package Services.Interfaces;

import models.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IBankService {

    @WebMethod
    String createAccountWithBalance(User user, double balance);  
    @WebMethod
    void transferMoney(String fromAccount, String toAccount, double amount);  
    @WebMethod
    void retireAccount(String accountNumber);  
    @WebMethod
    double getBalance(String accountNumber); 
}
