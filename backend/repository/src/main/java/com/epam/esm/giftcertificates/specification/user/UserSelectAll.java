package com.epam.esm.giftcertificates.specification.user;

import com.epam.esm.giftcertificates.entity.User;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * This class implements interface {@link SqlSpecification} for select all {@link User} from database.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class UserSelectAll implements SqlSpecification<User> {

    private Integer offset;
    private Integer limit;

    public UserSelectAll(final Integer offset, final Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypedQuery<User> buildQuery(final EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        return entityManager.createQuery(query.select(root))
                .setFirstResult(offset)
                .setMaxResults(limit);
    }

}
