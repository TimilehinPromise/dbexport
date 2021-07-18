package nugi.dbexport.dbexport;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity implementation class for Entity: BusinessSubcategory
 *
 */
@Entity
@Table(name="merchant_customer")
@NamedQuery(name="MerchantCustomer.findAll", query="SELECT m FROM MerchantCustomer m")
public class MerchantCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="merchant_ims_id")
    private String merchantUuid;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="trans_amount")
    private BigDecimal transAmount;

    @Column(name="order_amount")
    private BigDecimal orderAmount;

    @Column(name="total_trans")
    private Integer totalTrans;

    @Column(name="total_order")
    private Integer totalOrder;

//    //bi-directional many-to-one association to Customer
//    @ManyToOne
//    @JoinColumn(name="customer_id")
//    private Customer customer;

    public MerchantCustomer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMerchantUuid() {
        return merchantUuid;
    }

    public void setMerchantUuid(String merchantUuid) {
        this.merchantUuid = merchantUuid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getTotalTrans() {
        return totalTrans;
    }

    public void setTotalTrans(Integer totalTrans) {
        this.totalTrans = totalTrans;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }



}




