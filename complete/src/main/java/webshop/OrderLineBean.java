
package webshop;


public class OrderLineBean {
    int productId;
    int nrOfProducts;
    
    public OrderLineBean() {
        
    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getNrOfProducts() {
        return nrOfProducts;
    }

    public void setNrOfProducts(int nrOfProducts) {
        this.nrOfProducts = nrOfProducts;
    }
}
