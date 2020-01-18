package webshop;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    
    public List<Orders> findByOrderNumber(int orderNumber);
    
    public List<Orders> findByAccountId(int accountId);
    
    public List<Orders> findBySent(String sent);
}
