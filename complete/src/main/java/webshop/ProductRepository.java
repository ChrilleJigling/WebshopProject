/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webshop;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author melin
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{
    
    public List<Product> findByNameLike(String name);
}
