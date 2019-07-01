package com.epam.esm.giftcertificates.specification.tag;

import com.epam.esm.giftcertificates.entity.Tag;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * This class implements interface {@link SqlSpecification} for select popular tag by user id.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public class TagSelectByUserId implements SqlSpecification<Tag> {

    private static final String SELECT_BY_USER_ID = "SELECT t.id, t.name, SUM(gc.price), gc.price FROM tag t " +
            "INNER JOIN gift_certificate_m2m_tag gct  ON (gct.tag_id = t.id) " +
            "INNER JOIN gift_certificate gc ON (gc.id = gct.gift_certificate_id) " +
            "INNER JOIN purchase_m2m_gift_certificate pgc  ON (pgc.gift_certificate_id = gc.id) " +
            "INNER JOIN purchase p ON (p.id = pgc.purchase_id) WHERE p.certificate_user_id ='%d' " +
            "GROUP BY t.id, t.name, gc.price";
    private Long userId;

    public TagSelectByUserId(final Long userId) {
        this.userId = userId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Query buildQuery(final EntityManager entityManager) {
        return entityManager.createNativeQuery(String.format(SELECT_BY_USER_ID, userId), Tag.class);
    }

}
