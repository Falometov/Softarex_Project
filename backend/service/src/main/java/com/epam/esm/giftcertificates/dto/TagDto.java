package com.epam.esm.giftcertificates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Dto class for entity {@link com.epam.esm.giftcertificates.entity.Tag}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class TagDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "NotNull.tag.name")
    @Size(min = 2, max = 35, message = "Size.tag.name")
    @Pattern(regexp = "[A-Za-z-\\s\\d]+", message = "Pattern.tag.name")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String name;

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
