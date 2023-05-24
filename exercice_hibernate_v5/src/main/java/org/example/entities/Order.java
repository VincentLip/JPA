package org.example.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    @JoinTable(name = "order_produit",joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id"))
    private List<Produit> produitList;

    private double total;
    private Date dateOrder;

    public Order() {
    }

    public Order(List<Produit> produitList, double total, Date dateOrder) {
        this.produitList = produitList;
        this.total = total;
        this.dateOrder = dateOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        this.produitList = produitList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", produitList=" + produitList +
                ", total=" + total +
                ", dateOrder=" + dateOrder +
                '}';
    }
}
