package com.epam.esm.giftcertificates.dto;

import com.epam.esm.giftcertificates.entity.PurchaseCertificate;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Dto class for entity {@link PurchaseCertificate}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class PurchaseCertificateDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private GiftCertificateDto certificate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal purchasePrice;

    @NotNull(message = "NotNull.purchase.count")
    @Min(value = 1, message = "Min.purchase.count")
    @Max(value = Long.MAX_VALUE, message = "Max.purchase.count")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Long count;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "NotNull.purchase.certificateId")
    @Min(value = 1, message = "Min.purchase.certificateId")
    @Max(value = Long.MAX_VALUE, message = "Max.purchase.certificateId")
    private Long certificateId;

    public GiftCertificateDto getCertificate() {
        return certificate;
    }

    public void setCertificate(final GiftCertificateDto certificate) {
        this.certificate = certificate;
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

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(final Long certificateId) {
        this.certificateId = certificateId;
    }

}
