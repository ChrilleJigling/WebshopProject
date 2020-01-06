package webshop;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class WebshopService {
    
    @Autowired
    AccountRepository accountRepository;
    
    boolean isLoggedIn;
    Account account;
    
    public boolean login(String accountName, String password) {
        List<Account> a = accountRepository.findByUsername(accountName);
        if (a.size() > 0) {
            account = a.get(0);
            if (password.equals(account.getPassword())) {
                isLoggedIn = true;
            } 
        } 
        return isLoggedIn;
    }
}
