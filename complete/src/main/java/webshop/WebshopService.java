package webshop;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class WebshopService {

    Logger logger = LoggerFactory.getLogger(WebshopWebController.class);
    
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderLineRepository orderLineRepository;
    @Autowired
    OrdersRepository ordersRepository;

    static boolean isLoggedIn;
    static boolean isAdmin;
    static Account account;
    
    public boolean login(String accountName, String password) {
        isLoggedIn = false;
        isAdmin = false;

        List<Account> a = accountRepository.findByUsername(accountName);
        if (a.size() > 0) {
            account = a.get(0);
            if (password.equals(account.getPassword())) {
                if (account.getIsAdmin() == 1) {
                    isLoggedIn = true;
                    isAdmin = true;
                } else {
                    isLoggedIn = true;
                } 
            }
        }
        return isLoggedIn;
    }

    public List makeSearch(String keyword) {
        List<Product> searchList = productRepository.findByNameIgnoreCaseContaining(keyword);
        return searchList;
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
    
     public List getProductList(String category) {
        return productRepository.findByCategory(category);
    }
     
    public void addToCart(int productId, int nrOfProducts) {
        OrderLine orderLine = new OrderLine(1,1,productId, nrOfProducts);
        orderLineRepository.save(orderLine);
    }
    
    public List getProductListById(int productId) {
        return productRepository.findById(productId);
    }
    
    public void addProduct(String name, double price, String category){
    Product product = new Product(name, price, category);
    productRepository.save(product);
}
    
    public List getOrdersByOrderNumber() {
        return ordersRepository.findByAccountId(account.getId());
    }
    
    public List getOrders() {
        return ordersRepository.findAll();
    }
}
