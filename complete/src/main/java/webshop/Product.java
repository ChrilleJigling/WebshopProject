package webshop;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    private String name;
    private double price;
    
    @OneToMany(mappedBy="productId")
    private List<OrderLine> orderLinesList;

    public Product() {
        super();
    }

    public Product( String name, double price) {
        super();
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return product_id;
    }  

    public Product(Integer id) {
        this.product_id = id;
    }

    public Product(Integer id, String name, double price) {
        this.product_id = id;
        this.name = name;
        this.price = price;
    }

    public List<OrderLine> getOrderlinesList() {
        return orderLinesList;
    }

    public void setOrderlinesList(List<OrderLine> orderlinesList) {
        this.orderLinesList = orderlinesList;
    }
}
