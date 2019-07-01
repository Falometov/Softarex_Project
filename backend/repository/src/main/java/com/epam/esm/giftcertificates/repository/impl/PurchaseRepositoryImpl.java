package com.epam.esm.giftcertificates.repository.impl;

import com.epam.esm.giftcertificates.entity.Purchase;
import com.epam.esm.giftcertificates.repository.PurchaseRepository;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for entity {@link Purchase}.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@Repository
public class PurchaseRepositoryImpl implements PurchaseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public Purchase create(final Purchase purchase) {
        if (purchase.getId() == null) {
            entityManager.persist(purchase);
        } else {
            entityManager.merge(purchase);
        }
        return purchase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Purchase purchase) {
        entityManager.find(Purchase.class, purchase.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Purchase> findById(final Long id) {
        Purchase purchase = entityManager.find(Purchase.class, id);
        return purchase != null ? Optional.of(purchase) : Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Purchase> query(final SqlSpecification<Purchase> sqlSpecification) {
        return sqlSpecification.buildQuery(entityManager).getResultList();
    }

}
