
package jUnit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import webshop.WebshopService;

@RunWith(SpringJUnit4ClassRunner.class)
public class webshopServiceTest {
    
    @Autowired
    WebshopService webshopService;
    
    @Before
    public void setUp() {
       
    }
    
    @Test
    public void loginTest() {
        boolean isLoggedIn = webshopService.login("mellas", "mollas");
        assertTrue(isLoggedIn);
    }
    
    @Test
    public void addToCartTest() {
        webshopService.addToCart(1, 1);
    }
}
