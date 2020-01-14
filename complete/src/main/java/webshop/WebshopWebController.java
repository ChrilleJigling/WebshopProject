package webshop;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
            return "redirect:/accountPage";
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
            model.addAttribute("message", webshopService.account.getUsername());
            return "accountPage";
        } else {
            model.addAttribute("message", "You need to log in to access that page");
            return "redirect:/login";
        }
        
    }

    @PostMapping(path="/accountPage", params="keyword")
    public String search(@ModelAttribute SearchFormBean searchFormBean, Model model) {
        if (searchFormBean.keyword.length() > 0) {
            List<Product> searchResult = webshopService.makeSearch(searchFormBean.getKeyword());
            model.addAttribute("searchResults", searchResult);
        }
        return "/accountPage";
    }
    
        @PostMapping(path = "/accountPage", params = "orderLine")
    public String addToCart(@ModelAttribute OrderLine orderLine, Model model) {
        webshopService.addToCart(orderLine.getProductId(), orderLine.getNrOfProducts());
        return "/accountPage";
    }

    // ----- REGISTER ----- //
    @GetMapping("/register")
    public String register(Model model, LoginFormBean loginFormBean) {
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
