package com.epam.esm.giftcertificates.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This entity class stores info about gift certificate.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@Entity
@Table(name = "gift_certificate")
public class GiftCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "date_of_creation", nullable = false)
    private LocalDateTime dateOfCreation;

    @Column(name = "date_of_modification", nullable = false)
    private LocalDateTime dateOfModification;

    @Column(name = "duration_in_days", nullable = false)
    private Integer durationInDays;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "gift_certificate_m2m_tag",
            joinColumns = @JoinColumn(name = "gift_certificate_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "tag_id", nullable = false))
    private List<Tag> tags;

    public GiftCertificate() {
        tags = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GiftCertificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dateOfCreation=" + dateOfCreation +
                ", dateOfModification=" + dateOfModification +
                ", durationInDays=" + durationInDays +
                ", active=" + active +
                '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        boolean result;
        GiftCertificate giftCertificate;

        if (object != null) {
            giftCertificate = (GiftCertificate) object;
            if (this == object) {
                result = true;
            } else if (getClass() != object.getClass()) {
                result = false;
            } else {
                result = Objects.equals(id, giftCertificate.id) &&
                        Objects.equals(name, giftCertificate.name) &&
                        Objects.equals(description, giftCertificate.description) &&
                        Objects.equals(price, giftCertificate.price) &&
                        Objects.equals(dateOfCreation, giftCertificate.dateOfCreation) &&
                        Objects.equals(dateOfModification, giftCertificate.dateOfModification) &&
                        Objects.equals(durationInDays, giftCertificate.durationInDays) &&
                        Objects.equals(active, giftCertificate.active);
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
        return Objects.hash(id, name, description, price, dateOfCreation,
                dateOfModification, durationInDays, active);
    }

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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }

}
