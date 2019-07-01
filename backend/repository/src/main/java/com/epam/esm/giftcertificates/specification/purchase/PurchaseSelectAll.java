package com.epam.esm.giftcertificates.specification.purchase;

import com.epam.esm.giftcertificates.entity.Purchase;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * This class implements interface {@link SqlSpecification}
 * <p>for select all object {@link Purchase} from database</p>.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class PurchaseSelectAll implements SqlSpecification<Purchase> {

    private Integer offset;
    private Integer limit;

    public PurchaseSelectAll(final Integer offset, final Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Query buildQuery(final EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Purchase> query = builder.createQuery(Purchase.class);
        Root<Purchase> root = query.from(Purchase.class);

        query.select(root);
        return entityManager.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(limit);
    }

}
