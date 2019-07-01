package com.epam.esm.giftcertificates.specification.tag;

import com.epam.esm.giftcertificates.entity.Tag;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;

/**
 * This class implements {@link SqlSpecification}
 * <p>for select {@link Tag} by name</p>.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class SelectByName implements SqlSpecification<Tag> {

    private static final String FIELD_NAME = "name";
    private Object[] tagNames;

    public SelectByName(final Object... tagName) {
        this.tagNames = tagName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypedQuery<Tag> buildQuery(final EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> root = query.from(Tag.class);

        query.select(root);
        if (tagNames.length != 0) {
            query.where(root.get(FIELD_NAME).in(Arrays.asList(tagNames)));
        } else {
            query.where(builder.equal(root.get(FIELD_NAME), ""));
        }
        return entityManager.createQuery(query);
    }

}
