package com.epam.esm.giftcertificates.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entity class for many to many table.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@Entity
@Table(name = "purchase_m2m_gift_certificate")
public class PurchaseCertificate {

    @EmbeddedId
    private CompositeId compositeId;

    @Column(name = "purchase_price", nullable = false)
    private BigDecimal purchasePrice;

    @Column(name = "count", nullable = false)
    private Long count;

    @ManyToOne
    @MapsId("purchase_id")
    private Purchase purchase;

    @ManyToOne
    @MapsId("gift_certificate_id")
    private GiftCertificate giftCertificate;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "PurchaseCertificate{" +
                "compositeId=" + compositeId +
                ", purchasePrice=" + purchasePrice +
                ", count=" + count +
                '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        boolean result;
        PurchaseCertificate purchaseCertificate;

        if (object != null) {
            purchaseCertificate = (PurchaseCertificate) object;
            if (this == object) {
                result = true;
            } else if (getClass() != object.getClass()) {
                result = false;
            } else {
                result = Objects.equals(compositeId, purchaseCertificate.compositeId) &&
                        Objects.equals(purchasePrice, purchaseCertificate.purchasePrice) &&
                        Objects.equals(count, purchaseCertificate.count);
            }
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(compositeId, purchasePrice, count);
    }

    public CompositeId getCompositeId() {
        return compositeId;
    }

    public void setCompositeId(final CompositeId compositeId) {
        this.compositeId = compositeId;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(final BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(final Long count) {
        this.count = count;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(final Purchase purchase) {
        this.purchase = purchase;
    }

    public GiftCertificate getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(final GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

}
