/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author melin
 */
@RestController
public class webshopRestController {
    @Autowired
    WebshopService webshopService;
    
    	@GetMapping("/arch/rest/login/{username}/{password}")
	public ResponseEntity<String> login(@PathVariable("username") String username, @PathVariable("password")String password) {
		if (webshopService.login(username, password)) {
			return ResponseEntity.accepted().body("Logged in, welcome!");			
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("blö!");
		}
	}
}
