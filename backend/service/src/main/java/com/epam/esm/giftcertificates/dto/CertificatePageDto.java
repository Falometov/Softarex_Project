package com.epam.esm.giftcertificates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Dto class for pages and certificates.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class CertificatePageDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<GiftCertificateDto> certificates;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long pages;

    public CertificatePageDto(final List<GiftCertificateDto> certificates, final Long pages) {
        this.certificates = certificates;
        this.pages = pages;
    }

    public List<GiftCertificateDto> getCertificates() {
        return certificates;
    }

    public void setCertificates(final List<GiftCertificateDto> certificates) {
        this.certificates = certificates;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(final Long pages) {
        this.pages = pages;
    }

}
