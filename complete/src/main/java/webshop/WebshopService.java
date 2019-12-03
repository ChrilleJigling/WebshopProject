package webshop;

import java.util.Optional;
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
        Optional<Account> a = accountRepository.findByUsername(accountName);
        if (a.isPresent()) {
            account = a.get();
            if (password.equals(account.getPassword())) {
                isLoggedIn = true;
                return true;
            } 
        } 
        return false;
    }
}
