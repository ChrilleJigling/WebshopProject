package webshop;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    public Optional<Account> findByUsername(String name);
    
    public Optional<Account> findById(int id);
}
