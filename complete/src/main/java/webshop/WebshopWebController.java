package webshop;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebshopWebController {

    Logger logger = LoggerFactory.getLogger(WebshopWebController.class);

    @Autowired
    WebshopService webshopService;

    // ----- LOGIN ----- //
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginFormBean", new LoginFormBean());
        model.addAttribute("message", "Please login:");
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginFormBean loginFormBean, Model model) {
        if (webshopService.login(loginFormBean.username, loginFormBean.password)) {
            if (webshopService.isAdmin) {
                return "redirect:/adminAccountPage";
            } else {
                return "redirect:/accountPage";
            }
        } else {
            model.addAttribute("message", "No such user, try again");
            return "login";
        }
    }

    // ----- ACCOUNT PAGE ----- //
    @GetMapping("/accountPage")
    public String accountInformation(Model model) {
        if (webshopService.isLoggedIn) {
            model.addAttribute("searchFormBean", new SearchFormBean());
            model.addAttribute("orderLineBean", new OrderLineBean());
            model.addAttribute("message", webshopService.account.getUsername());
            List<Product> searchResult = webshopService.makeSearch("ArCh");
            model.addAttribute("searchResults", searchResult);
            return "accountPage";
        } else {
            model.addAttribute("message", "You need to log in to access that page");
            return "redirect:/login";
        }
    }

    @PostMapping(path = "/accountPage", params = "keyword")
    public String search(@ModelAttribute SearchFormBean searchFormBean, Model model) {
        if (searchFormBean.keyword.length() > 0) {
            List<Product> searchResult = webshopService.makeSearch(searchFormBean.getKeyword());
            model.addAttribute("searchResults", searchResult);
            model.addAttribute("searchFormBean", new SearchFormBean());
            model.addAttribute("orderLineBean", new OrderLineBean());
        }
        return "/accountPage";
    }

    @PostMapping(path = "/accountPage")
    public String addToCart(@ModelAttribute OrderLineBean orderLineBean, Model model) {
        webshopService.createOrder();
        webshopService.addToCart(orderLineBean.getProductId(), orderLineBean.getNrOfProducts());
        return "redirect:/accountPage";
    }
    
    @GetMapping("/shoppingCart")
    public String linkToShoppingCart(Model model) {
        List shopCart = webshopService.getShoppingCart();
        model.addAttribute("shopCart", shopCart);
        model.addAttribute("totalPrice", webshopService.getTotalPrice());
        return "/shoppingCart";
    }

    // ----- ADMIN ----- //
    @GetMapping("/addProduct")
    public String linkToAddProduct(@ModelAttribute ProductBean productBean, Model model) {
        if (webshopService.isAdmin) {
            model.addAttribute("product", new ProductBean());
            return "/addProduct";
        }
        return "redirect:/login";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute ProductBean productBean, Model model) {
        webshopService.addProduct(productBean.getName(), productBean.getPrice(), productBean.getCategory());
        return "adminAccountPage";
    }

    @GetMapping("/adminAccountPage")
    public String adminAccountPage(Model model) {
        if (webshopService.isAdmin) {
            model.addAttribute("message", webshopService.account.getUsername());
            return "/adminAccountPage";
        } else {
            return "redirect:/login";
        }
    }

    // ----- ORDERS / ORDERLINE ----- //
    
    @PostMapping("/markAsSent")
    public String orders(@ModelAttribute OrdersBean orderBean, Model model) {
        webshopService.markOrderAsSent(orderBean.getOrderNumber());
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String linkToOrders(Model model) {
        if (webshopService.isAdmin) {
            model.addAttribute("orders", webshopService.getOrders());
            model.addAttribute("message", webshopService.account.getUsername());
            return "/orders";
        } else if(!webshopService.isAdmin) {
            model.addAttribute("orders", webshopService.getOrdersByAccount());
            model.addAttribute("message", webshopService.account.getUsername());
            return "/orders";
        } 
            return "redirect:/login";  
    }
    
    @PostMapping("/orderDetails")
    public String linkToOrderDetails(@ModelAttribute OrdersBean orderBean, Model model) {
        model.addAttribute("orderLines", webshopService.getOrderLineList(orderBean.getOrderNumber()));
        return "/orderDetails";
    }
    
    @PostMapping("/delete")
    public String deleteOrderLine(@ModelAttribute OrderLineBean orderLine, Model model) {
        webshopService.deleteOrderLine(orderLine.productId);
        return "redirect:/shoppingCart";
    }
    
    @PostMapping("/updateQuantity")
    public String updateQuantity (@ModelAttribute OrderLineBean orderLine, Model model) {
        webshopService.updateOrderLine(orderLine.productId,orderLine.nrOfProducts);
        return "redirect:/shoppingCart";
    }
    
    @PostMapping("/makeOrder")
    public String makeOrder (@ModelAttribute OrderLineBean orderLine, Model model) {
        webshopService.updateOrdered(orderLine.getOrderNumber());
        return "redirect:/orderConfirmation";
    }
    
    @GetMapping("/orderConfirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("orderConfirmation", webshopService.getOrderConfirmation());
        model.addAttribute("totalPrice", webshopService.getTotalPrice());
        return "/orderConfirmation";
    }

    /* @GetMapping("/orderDetails")
    public String linkToOrderDetails2(Model model) {
        if(webshopService.isAdmin){
            model.addAttribute("orderLines", webshopService.getOrderLineList(3));
            model.addAttribute("message", webshopService.account.getUsername());
            return "redirect:/orderDetails";
        } else {
            return "redirect:/orderDetails";
        }
    }*/
    
    // ----- REGISTER ----- //
    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute LoginFormBean loginFormBean, Model model) {
        if (webshopService.isUsernameAvailable(loginFormBean.getUsername())) {
            if (webshopService.isPasswordSecure(loginFormBean.getPassword())) {
                webshopService.registerAccount(loginFormBean.getUsername(), loginFormBean.getPassword());
                return "login";
            } else {
                model.addAttribute("message", "Password is too short");
                return "register";
            }
        } else {
            model.addAttribute("message", "That username is already in use");
            return "register";
        }
    }

    // ----- PC ----- //
    @GetMapping("/pc")
    public String linkToPC(Model model) {
        model.addAttribute("products", webshopService.getProductList("PC"));
        return "pc";
    }

    // ----- LAPTOP ----- //
    @GetMapping("/laptop")
    public String linkToLaptop(Model model) {
        model.addAttribute("products", webshopService.getProductList("Laptop"));
        return "laptop";
    }

    // ----- MONITOR ----- //
    @GetMapping("/monitor")
    public String linkToMonitor(Model model) {
        model.addAttribute("products", webshopService.getProductList("Monitor"));
        return "monitor";
    }

    // ----- HEADSET ----- //
    @GetMapping("/headset")
    public String linkToHeadset(Model model) {
        model.addAttribute("products", webshopService.getProductList("Headset"));
        return "headset";
    }

    // ----- KEYBOARD ----- //
    @GetMapping("/keyboard")
    public String linkToKeyboard(Model model) {
        model.addAttribute("products", webshopService.getProductList("Keyboard"));
        return "keyboard";
    }

    // ----- MOUSE ----- //
    @GetMapping("/mouse")
    public String linkToMouse(Model model) {
        model.addAttribute("products", webshopService.getProductList("Mouse"));
        return "mouse";
    }
}
