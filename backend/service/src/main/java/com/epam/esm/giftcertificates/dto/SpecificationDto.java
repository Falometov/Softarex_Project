package com.epam.esm.giftcertificates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Dto class for URL parameters.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class SpecificationDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String[] tags;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String description;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String sortParam;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String sortOrder;

    public String[] getTags() {
        return tags;
    }

    public void setTags(final String[] tags) {
        this.tags = tags;
    }

    public String getName() {
        return this.name;
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

    public String getSortParam() {
        return this.sortParam;
    }

    public void setSortParam(final String sortParam) {
        this.sortParam = sortParam;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(final String sortOrder) {
        this.sortOrder = sortOrder;
    }

}
