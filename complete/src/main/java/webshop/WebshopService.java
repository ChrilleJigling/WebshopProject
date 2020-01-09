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
@Autowired
    ProductRepository productRepository;
    static boolean isLoggedIn;
    static Account account;

    public boolean login(String accountName, String password) {
        isLoggedIn = false;
        List<Account> a = accountRepository.findByUsername(accountName);
        if (a.size() > 0) {
            account = a.get(0);
            if (password.equals(account.getPassword())) {
                isLoggedIn = true;
            }
        }
        return isLoggedIn;
    }

    public List makeSearch(String keyword) {
    return productRepository.findByNameLike(keyword);
    }

    public boolean isUsernameAvailable(String username) {
        List<Account> a = accountRepository.findByUsername(username);
        if (a.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isPasswordSecure(String password) {
        if (password.length() > 3) {
            return true;
        } else {
            return false;
        }
    }

    public void registerAccount(String username, String password) {
        Account account = new Account(username, password);
        accountRepository.save(account);
    }
}
