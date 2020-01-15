package webshop;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class OrderLineId implements Serializable {

    private int orderNumber;
    private int productId;
    private int accountId;
    
    public OrderLineId() {
        
    }
    
    public OrderLineId(int orderNumber, int productId, int accountId) {
        this.orderNumber = orderNumber;
        this.productId = productId;
        this.accountId = accountId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getProductId() {
        return productId;
    }

    public int getAccountId() {
        return accountId;
    }
    
    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            return result;
    }

    @Override
    public boolean equals(Object obj) {
            return true;
    }
}
