package com.epam.esm.giftcertificates.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Dto class for entity {@link com.epam.esm.giftcertificates.entity.GiftCertificate}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class GiftCertificateDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "NotNull.certificate.name")
    @Size(min = 2, max = 35, message = "Size.certificate.name")
    @Pattern(regexp = "[A-Za-z-\\d\\s]+", message = "Pattern.certificate.name")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String name;

    @NotNull(message = "NotNull.certificate.description")
    @Size(min = 10, message = "Size.certificate.description")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String description;

    @NotNull(message = "NotNull.certificate.price")
    @Min(value = 1, message = "Min.certificate.price")
    @Max(value = Long.MAX_VALUE, message = "Max.certificate.price")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private BigDecimal price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dateOfCreation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private LocalDateTime dateOfModification;

    @NotNull(message = "NotNull.certificate.durationInDays")
    @Min(value = 1, message = "Min.certificate.durationInDays")
    @Max(value = 31, message = "Max.certificate.durationInDays")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Integer durationInDays;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean active;

    @Valid
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private List<TagDto> tags;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(final LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDateTime getDateOfModification() {
        return dateOfModification;
    }

    public void setDateOfModification(final LocalDateTime dateOfModification) {
        this.dateOfModification = dateOfModification;
    }

    public Integer getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(final Integer durationInDays) {
        this.durationInDays = durationInDays;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(final List<TagDto> tags) {
        this.tags = tags;
    }

}
