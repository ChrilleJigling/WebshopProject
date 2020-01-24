package webshop;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    
    public List<Orders> findByOrderNumber(int orderNumber);
    
    public List<Orders> findByAccountId(int accountId);
    
    public List<Orders> findBySent(String sent);
    
    @Query("SELECT o FROM Orders o WHERE o.accountId = :accountId AND o.sent = :sent")
    public List<Orders> findByAccountIdAndSent(@Param("accountId") int accountId, @Param("sent") String sent);
}
