
package webshop;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderLineRepository extends JpaRepository<OrderLine, Integer>{
    
    public List<OrderLine> findByAccountId(int accountId);
    
    public List<OrderLine> findByProductId(int productId);
    
    public List<OrderLine> findByOrderNumber(int orderNumber);
}
