package com.epam.esm.giftcertificates.service;

import com.epam.esm.giftcertificates.dto.*;
import com.epam.esm.giftcertificates.entity.GiftCertificate;
import com.epam.esm.giftcertificates.service.api.ServiceApi;

/**
 * Service interface for entity {@link GiftCertificate}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public interface CertificateService extends ServiceApi<GiftCertificateDto> {

    /**
     * This method update object in database.
     *
     * @param giftCertificate value of the object {@link GiftCertificateDto}
     * @return value of the object {@link GiftCertificateDto}
     */
    GiftCertificateDto update(GiftCertificateDto giftCertificate);

    /**
     * This method update certificate price.
     *
     * @param priceDto value of the object {@link PriceDto}
     * @return value of the object {@link GiftCertificateDto}
     */
    GiftCertificateDto update(PriceDto priceDto);

    /**
     * This method find objects {@link GiftCertificate} by some specification.
     *
     * @param specification value of the object {@link SpecificationDto}
     * @param pagination    value of the object {@link PaginationDto}
     * @return value of the object {@link CertificatePageDto}
     */
    CertificatePageDto findBySpecification(SpecificationDto specification, PaginationDto pagination);

    /**
     * This method find user certificates by user id.
     *
     * @param pagination value of the object {@link PaginationDto}
     * @param username   value of the username
     * @return value of the object {@link CertificatePageDto}
     */
    CertificatePageDto findByUsername(String username, PaginationDto pagination);

    /**
     * This method return value of the certificates count.
     *
     * @param specification value of the object {@link SpecificationDto}
     * @return value of the certificates count
     */
    Long getCertificatesCount(SpecificationDto specification);

}
