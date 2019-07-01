package com.epam.esm.giftcertificates.specification.giftcertificate;

import com.epam.esm.giftcertificates.entity.GiftCertificate;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * This class implements {@link SqlSpecification}
 * <p>for select all {@link GiftCertificate} from database</p>.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
public class CertificateSelectAll implements SqlSpecification<GiftCertificate> {

    private static final String FIELD_ACTIVE = "active";
    private Integer offset;
    private Integer limit;

    public CertificateSelectAll(final Integer offset, final Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypedQuery<GiftCertificate> buildQuery(final EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> query = builder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> root = query.from(GiftCertificate.class);

        query.select(root).where(builder.equal(root.get(FIELD_ACTIVE), true));
        return entityManager.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(limit);
    }

}
