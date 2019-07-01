package com.epam.esm.giftcertificates.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Dto class for entity {@link com.epam.esm.giftcertificates.entity.Purchase}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class PurchaseDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal cost;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime timestamp;

    @Valid
    @NotNull(message = "NotNull.purchase.certificates")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private List<PurchaseCertificateDto> certificates;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDto user;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String login;

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

    public List<PurchaseCertificateDto> getCertificates() {
        return certificates;
    }

    public void setCertificates(final List<PurchaseCertificateDto> certificates) {
        this.certificates = certificates;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(final UserDto user) {
        this.user = user;
    }

}
