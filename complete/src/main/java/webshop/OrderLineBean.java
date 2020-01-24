package webshop;


public class OrderLineBean {
    int productId;
    int nrOfProducts;
    int orderNumber;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
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
