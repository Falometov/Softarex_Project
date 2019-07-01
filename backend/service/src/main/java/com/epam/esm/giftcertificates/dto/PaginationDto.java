package com.epam.esm.giftcertificates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Dto class for pagination parameters.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class PaginationDto {

    @Min(value = 0, message = "Min.pagination.offset")
    @Max(value = Long.MAX_VALUE, message = "Max.pagination.offset")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer offset;

    @Min(value = 1, message = "Min.pagination.limit")
    @Max(value = 50, message = "Max.pagination.limit")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer limit;

    public PaginationDto(final Integer offset, final Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(final Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

}
