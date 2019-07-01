package com.epam.esm.giftcertificates.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This entity class stores info about purchase.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseCertificate> purchaseCertificates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_user_id", nullable = false)
    private User user;

    public Purchase() {
        purchaseCertificates = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", cost=" + cost +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        boolean result;
        Purchase purchase;

        if (object != null) {
            purchase = (Purchase) object;
            if (this == object) {
                result = true;
            } else if (getClass() != object.getClass()) {
                result = false;
            } else {
                result = Objects.equals(id, purchase.id) &&
                        Objects.equals(cost, purchase.cost) &&
                        Objects.equals(timestamp, purchase.timestamp) &&
                        Objects.equals(user, purchase.user);
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, cost, timestamp, user);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(final BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<PurchaseCertificate> getPurchaseCertificates() {
        return purchaseCertificates;
    }

    public void setPurchaseCertificates(final List<PurchaseCertificate> purchaseCertificates) {
        this.purchaseCertificates = purchaseCertificates;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

}
