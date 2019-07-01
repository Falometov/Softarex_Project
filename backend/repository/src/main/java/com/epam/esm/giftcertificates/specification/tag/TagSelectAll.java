package com.epam.esm.giftcertificates.specification.tag;

import com.epam.esm.giftcertificates.entity.Tag;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * This class implements interface {@link SqlSpecification}
 * <p>for select all {@link Tag} from database</p>.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class TagSelectAll implements SqlSpecification<Tag> {

    private Integer offset;
    private Integer limit;

    public TagSelectAll(final Integer offset, final Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypedQuery<Tag> buildQuery(final EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> root = query.from(Tag.class);

        return entityManager.createQuery(query.select(root))
                .setFirstResult(offset)
                .setMaxResults(limit);
    }

}
