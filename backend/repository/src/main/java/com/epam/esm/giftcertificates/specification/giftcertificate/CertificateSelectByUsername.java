package com.epam.esm.giftcertificates.specification.giftcertificate;

import com.epam.esm.giftcertificates.entity.GiftCertificate;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * This class implements interface {@link SqlSpecification}
 * <p>for select all {@link GiftCertificate} by username from database</p>.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
public class CertificateSelectByUsername implements SqlSpecification<GiftCertificate> {

    private static final String SELECT_COUNT = "SELECT COUNT(gc.*) " +
            "FROM (SELECT DISTINCT gc.* FROM gift_certificate AS gc " +
            "INNER JOIN purchase_m2m_gift_certificate AS pg ON pg.gift_certificate_id = gc.id " +
            "INNER JOIN purchase AS p ON p.id = pg.purchase_id " +
            "INNER JOIN certificate_user AS cu ON cu.id = p.certificate_user_id WHERE cu.username = '%s') AS gc";
    private static final String SELECT_BY_USERNAME = "SELECT DISTINCT gc.* FROM gift_certificate AS gc " +
            "INNER JOIN purchase_m2m_gift_certificate AS pg ON pg.gift_certificate_id = gc.id " +
            "INNER JOIN purchase AS p ON p.id = pg.purchase_id " +
            "INNER JOIN certificate_user AS cu ON cu.id = p.certificate_user_id WHERE cu.username = '%s'";
    private String username;
    private Integer offset;
    private Integer limit;
    private boolean countFlag;

    public CertificateSelectByUsername(final String username, final Integer offset,
                                       final Integer limit, final boolean countFlag) {
        this.username = username;
        this.offset = offset;
        this.limit = limit;
        this.countFlag = countFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Query buildQuery(final EntityManager entityManager) {
        Query query;

        if (!countFlag) {
            query = entityManager.createNativeQuery(String.format(SELECT_BY_USERNAME, username), GiftCertificate.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit);
        } else {
            query = entityManager.createNativeQuery(String.format(SELECT_COUNT, username));
        }

        return query;
    }

}
