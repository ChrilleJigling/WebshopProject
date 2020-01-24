
package webshop;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Table(name="orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number")
    private int orderNumber;
    
    @Column(name = "account_id")
    private int accountId;
    
    @Column(name = "sent")
    private String sent = "NO";
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="orderNumber")
    private List<OrderLine> orderLines;
    
    @ManyToOne
    @JoinColumn(name = "account_id", insertable=false, updatable=false)
    private Account account;
    
    @Column(name = "ordered")
    private String ordered;
    
    public Orders() {
        super();
    }

    public String getOrdered() {
        return ordered;
    }

    public void setOrdered(String ordered) {
        this.ordered = ordered;
    }
    
     public Orders(int accountsId) {
        super();
        this.accountId = accountsId;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }
     
    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getAccountsId() {
        return accountId;
    }

    public void setAccountsId(int accountsId) {
        this.accountId = accountsId;
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }

}
