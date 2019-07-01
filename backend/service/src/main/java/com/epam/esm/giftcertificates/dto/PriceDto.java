package com.epam.esm.giftcertificates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Dto class for certificate price.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class PriceDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "NotNull.certificate.price")
    @Min(value = 1, message = "Min.certificate.price")
    @Max(value = Long.MAX_VALUE, message = "Max.certificate.price")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

}
