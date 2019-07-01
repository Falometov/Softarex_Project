package com.epam.esm.giftcertificates.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * This class stores values of the certificate and purchase ids.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@Embeddable
public class CompositeId implements Serializable {

    @Column(name = "gift_certificate_id", nullable = false)
    private Long certificateId;

    @Column(name = "purchase_id", nullable = false)
    private Long purchaseId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CompositeId{" +
                "certificateId=" + certificateId +
                ", purchaseId=" + purchaseId +
                '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        boolean result;
        CompositeId compositeId;

        if (object != null) {
            compositeId = (CompositeId) object;
            if (this == object) {
                result = true;
            } else if (getClass() != object.getClass()) {
                result = false;
            } else {
                result = Objects.equals(certificateId, compositeId.certificateId) &&
                        Objects.equals(purchaseId, compositeId.purchaseId);
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
        return Objects.hash(certificateId, purchaseId);
    }

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(final Long certificateId) {
        this.certificateId = certificateId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(final Long purchaseId) {
        this.purchaseId = purchaseId;
    }

}
