/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webshop;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class webshopRestController {

    @Autowired
    WebshopService webshopService;

    @GetMapping("/arch/rest/login/{username}/{password}")
    public ResponseEntity<String> login(@PathVariable("username") String username, @PathVariable("password") String password) {
        if (webshopService.login(username, password)) {
            return ResponseEntity.accepted().body("Logged in, welcome!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(webshopService.error());
        }
    }

    @GetMapping("/arch/rest/addProduct/{name}/{price}/{category}")
    public ResponseEntity<String> addToCart(@PathVariable("name") String name, @PathVariable("price") int price, @PathVariable("category") String category) {
        if (webshopService.isLoggedIn && webshopService.isAdmin) {
            webshopService.addProduct(name, price, category);
            return ResponseEntity.accepted().body("Product added");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(webshopService.error());
        }
    }

    @GetMapping("/arch/rest/unsentOrders")
    public ResponseEntity showUnsentOrders() {
        if (webshopService.isLoggedIn && webshopService.isAdmin) {
            List unsentOrders = webshopService.getUnsentOrders();
            return ResponseEntity.accepted().body(unsentOrders);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(webshopService.error());
        }
    }
    @GetMapping("/arch/rest/sentOrders")
    public ResponseEntity showSentOrders() {
        if (webshopService.isLoggedIn && webshopService.isAdmin) {
            List sentOrders = webshopService.getSentOrders();
            return ResponseEntity.accepted().body(sentOrders);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(webshopService.error());
        }
    }
     @GetMapping("/arch/rest/send/{orderNumber}")
    public ResponseEntity<String> markOrderAsSent(@PathVariable("orderNumber") int orderNumber){
        if (webshopService.isLoggedIn && webshopService.isAdmin) {
           webshopService.markOrderAsSent(orderNumber);
           return ResponseEntity.accepted().body("Order is now sent");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(webshopService.error());
        }
    }
}
