package webshop;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    public List<Account> findByUsername(String username);
    
    public Optional<Account> findById(int id);
}
