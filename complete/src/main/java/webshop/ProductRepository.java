
package webshop;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer>{
    
    public List<Product> findByNameIgnoreCaseContaining(String name);
    
    public List<Product> findByCategory(String category);
    
    public List<Product> findById(int id);
}
