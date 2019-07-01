package com.epam.esm.giftcertificates.repository.impl;

import com.epam.esm.giftcertificates.entity.GiftCertificate;
import com.epam.esm.giftcertificates.repository.CertificateRepository;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for entity {@link GiftCertificate}.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@Repository
public class CertificateRepositoryImpl implements CertificateRepository {

    private static final String FIELD_ID = "id";
    private static final String FIELD_ACTIVE = "active";

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public GiftCertificate create(final GiftCertificate giftCertificate) {
        entityManager.persist(giftCertificate);
        return giftCertificate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GiftCertificate update(final GiftCertificate giftCertificate) {
        entityManager.merge(giftCertificate);
        return giftCertificate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final GiftCertificate giftCertificate) {
        entityManager.remove(entityManager.find(GiftCertificate.class, giftCertificate.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GiftCertificate> findById(final Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> query = builder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> root = query.from(GiftCertificate.class);

        query.select(root).where(builder.and(builder.equal(root.get(FIELD_ID), id),
                builder.equal(root.get(FIELD_ACTIVE), true)));
        return entityManager.createQuery(query).getResultStream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GiftCertificate> query(final SqlSpecification<GiftCertificate> sqlSpecification) {
        return sqlSpecification.buildQuery(entityManager).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    public Long getCertificatesCount(final SqlSpecification<GiftCertificate> sqlSpecification) {
        return ((BigInteger) sqlSpecification.buildQuery(entityManager).getSingleResult()).longValue();
    }

}
