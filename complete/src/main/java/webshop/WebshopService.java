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
     
    public void createOrder() {
        List<Orders> orderList = ordersRepository.findByAccountId(account.getId());
        if (orderList.isEmpty()) {
            Orders order = new Orders(account.getId());
            ordersRepository.save(order);
        }
        for (Orders orders : orderList) {
            if(orderList.size() > 0 && orders.getSent() == "YES") {
                Orders order2 = new Orders(account.getId());
                ordersRepository.save(order2);
            } 
        }
    }
     
    public void addToCart(int productId, int nrOfProducts) {
        List<Orders> ordersList = ordersRepository.findByAccountId(account.getId());
        Orders order = new Orders();
        order = ordersList.get(0);
        OrderLine orderLine = new OrderLine(order.getOrderNumber(), productId, account.getId(), nrOfProducts);
        orderLineRepository.save(orderLine);
    }

    public List getOrderLineList(int orderNumber) {
        return orderLineRepository.findByOrderNumber(orderNumber);
    }
    
    public List getOrderLineListByAccountId() {
        return orderLineRepository.findByAccountId(account.getId());
    }

    public List getProductListById(int productId) {
        return productRepository.findById(productId);
    }

    public void addProduct(String name, double price, String category) {
        Product product = new Product(name, price, category);
        productRepository.save(product);
    }

    public List getOrdersByOrderNumber() {
        return ordersRepository.findByAccountId(account.getId());
    }

    public List getOrders() {
        return ordersRepository.findAll();
    }
    
    public List getShoppingCart() {
        List<Orders> orderList = ordersRepository.findByAccountIdAndSent(account.getId(), "NO");
        String order = String.valueOf(orderList.get(0));
        logger.info(order);
        logger.info("SHOPPINGCART");
        return orderList;
    }
    
    public List getOrdersByAccount() {
        return ordersRepository.findByAccountId(account.getId());
    }

    public void markOrderAsSent(int orderNumber) {
        List<Orders> orders = ordersRepository.findByOrderNumber(orderNumber);
        Orders order = orders.get(0);
        order.setSent("YES");
        ordersRepository.save(order);
    }
}
