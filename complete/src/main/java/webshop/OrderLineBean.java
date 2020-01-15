/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webshop;

/**
 *
 * @author melin
 */
public class OrderLineBean {
    int productId;
    int nrOfProducts;

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
