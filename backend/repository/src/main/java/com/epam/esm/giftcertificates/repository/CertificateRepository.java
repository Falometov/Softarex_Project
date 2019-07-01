package com.epam.esm.giftcertificates.repository;

import com.epam.esm.giftcertificates.entity.GiftCertificate;
import com.epam.esm.giftcertificates.repository.api.RepositoryApi;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

/**
 * Repository interface for entity {@link GiftCertificate}.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
public interface CertificateRepository extends RepositoryApi<GiftCertificate> {

    /**
     * This method update gift certificate object in database.
     *
     * @param giftCertificate value of the entity {@link GiftCertificate}
     */
    GiftCertificate update(GiftCertificate giftCertificate);

    /**
     * This method return value of the certificates count.
     *
     * @param sqlSpecification value of the object {@link SqlSpecification}
     * @return value of the certificates count
     */
    Long getCertificatesCount(SqlSpecification<GiftCertificate> sqlSpecification);

}
