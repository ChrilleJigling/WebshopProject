
package webshop;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@IdClass(OrderLineId.class)
@Table(name="orderline")
public class OrderLine implements Serializable {

    @Id
    @Column(name = "order_number")
    private int orderNumber;

    @Id
    @Column(name = "product_id")
    private int productId;
    
    @Column(name = "number_of_products")
    private int nrOfProducts;
    
    @Id
    @Column(name = "account_id")
    private int accountId;

    @ManyToOne
    @JoinColumn(name = "order_number", insertable=false, updatable=false)
    private Orders orders;
    
    @ManyToOne
    @JoinColumn(name = "product_id", insertable=false, updatable=false)
    private Product product;
    
    public OrderLine() {
    }

    public OrderLine(int orderNumber, int productId, int accountId, int nrOfProducts) {
        this.productId = productId;
        this.accountId= accountId;
        this.nrOfProducts = nrOfProducts;
        this.orderNumber = orderNumber;
    }
    public int getNrOfProducts() {
        return nrOfProducts;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setNrOfProducts(int nrOfProducts) {
        this.nrOfProducts = nrOfProducts;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

}
