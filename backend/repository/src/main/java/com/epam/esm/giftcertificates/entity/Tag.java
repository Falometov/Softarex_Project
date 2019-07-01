package com.epam.esm.giftcertificates.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * This entity class stores tag name of gift certificate.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 35, unique = true)
    private String name;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Tag{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        boolean result;
        Tag tag;

        if (object != null) {
            tag = (Tag) object;
            if (this == object) {
                result = true;
            } else if (getClass() != object.getClass()) {
                result = false;
            } else {
                result = Objects.equals(id, tag.id) && Objects.equals(name, tag.name);
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
        return Objects.hash(id, name);
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

}
