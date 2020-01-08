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

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginFormBean", new LoginFormBean());
        model.addAttribute("message", "Please login:");
        return "login";
    }

    @GetMapping("/accountPage")
    public String accountInformation(Model model) {
        model.addAttribute("message", webshopService.isLoggedIn);
        return "accountPage";
    }

    @GetMapping("/register")
    public String register(Model model, LoginFormBean loginFormBean) {
        return "register";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginFormBean loginFormBean, Model model) {
        if (webshopService.login(loginFormBean.username, loginFormBean.password)) {
            model.addAttribute("username", loginFormBean.getUsername());
            model.addAttribute("password", loginFormBean.getPassword());
            return "redirect:/accountPage";
        } else {
            model.addAttribute("message", "No such user, try again");
            return "login";
        }
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
}
