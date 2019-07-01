package com.epam.esm.giftcertificates.specification.user;

import com.epam.esm.giftcertificates.entity.User;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * This class implements interface {@link SqlSpecification}
 * <p>for select object {@link User} from database</p>.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class SelectByUsername implements SqlSpecification<User> {

    private static final String FIELD_USERNAME = "username";
    private String username;
    private Integer offset;
    private Integer limit;

    public SelectByUsername(final String username, final Integer offset, final Integer limit) {
        this.username = username;
        this.offset = offset;
        this.limit = limit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypedQuery<User> buildQuery(final EntityManager entityManager) {
        TypedQuery<User> typedQuery;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        query.select(root).where(builder.equal(root.get(FIELD_USERNAME), username));
        typedQuery = entityManager.createQuery(query);

        if (offset != null && limit != null) {
            typedQuery.setFirstResult(offset).setMaxResults(limit);
        }

        return typedQuery;
    }

}
