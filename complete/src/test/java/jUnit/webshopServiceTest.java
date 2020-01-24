
package jUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import webshop.WebshopService;

public class webshopServiceTest {
    
    @Autowired
    WebshopService webshopService;
    
    @Before
    public void setUp() {
       
    }
    
    @Test
    public void loginTest() {
        boolean isLoggedIn = webshopService.login("chrille", "1234");
        assertTrue(isLoggedIn);
    }
    
    @Test
    public void addToCartTest() {
        webshopService.addToCart(1, 1);
    }
}
