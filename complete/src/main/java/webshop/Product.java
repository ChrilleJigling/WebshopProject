package webshop;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    private String name;
    private double price;
    private String category;

    @OneToMany(mappedBy = "productId")
    private List<OrderLine> orderLinesList;

    public Product() {
        super();
    }

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<OrderLine> getOrderLinesList() {
        return orderLinesList;
    }

    public void setOrderLinesList(List<OrderLine> orderLinesList) {
        this.orderLinesList = orderLinesList;
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

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public List<OrderLine> getOrderlinesList() {
        return orderLinesList;
    }

    public void setOrderlinesList(List<OrderLine> orderlinesList) {
        this.orderLinesList = orderlinesList;
    }

    @Override
    public String toString() {
        return name + " " + price + ":-\n";
    }

}
