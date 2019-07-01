package com.epam.esm.giftcertificates.specification.giftcertificate;

import com.epam.esm.giftcertificates.entity.GiftCertificate;
import com.epam.esm.giftcertificates.exception.InvalidRequestParameterException;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class implements interface {@link SqlSpecification}
 * <p>for select all {@link GiftCertificate} by tag from database</p>.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
public class SelectBySpecification implements SqlSpecification<GiftCertificate> {

    private static final String SELECT_COUNT = "SELECT COUNT(g.*) FROM gift_certificate AS g WHERE g.active=true ";
    private static final String SELECT_COUNT_BY_TAG = "SELECT COUNT(g.*) FROM gift_certificate  AS g "
            + "INNER JOIN gift_certificate_m2m_tag AS ct ON ct.gift_certificate_id = g.id "
            + "INNER JOIN tag AS t ON t.id = ct.tag_id WHERE t.name IN (%s) AND g.active=true ";
    private static final String SELECT_ALL = "SELECT * FROM gift_certificate AS g WHERE g.active=true ";
    private static final String SELECT_BY_TAG = "SELECT g.* FROM gift_certificate  AS g "
            + "INNER JOIN gift_certificate_m2m_tag AS ct ON ct.gift_certificate_id = g.id "
            + "INNER JOIN tag AS t ON t.id = ct.tag_id WHERE t.name IN (%s) AND g.active=true "
            + "GROUP BY g.id HAVING COUNT(t.id)=%d";
    private static final String SEARCH_BY_NAME_WITH_TAG = "AND POSITION('%s' IN g.name) > 0 ";
    private static final String SEARCH_BY_DESCRIPTION_WITH_TAG = "AND POSITION('%s' IN g.description) > 0 ";
    private static final String ORDER_BY = "ORDER BY g.%s ";
    private static final String SORT_PARAMETER_INCORRECT = "Incorrect.sortParameter";
    private static final String SORT_ORDER_INCORRECT = "Incorrect.sortOrder";
    private static final String NAME = "name";
    private static final String DATE_OF_MODIFICATION = "date_of_modification";
    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private String[] tags;
    private String name;
    private String description;
    private String sortParam;
    private String sortOrder;
    private Integer offset;
    private Integer limit;
    private boolean countFlag;

    public SelectBySpecification(final String[] tags, final String name,
                                 final String description, final String sort,
                                 final String sortOrder, final Integer offset,
                                 final Integer limit, boolean countFlag) {
        this.tags = tags;
        this.name = name;
        this.description = description;
        this.sortParam = sort;
        this.sortOrder = sortOrder;
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
        String sqlQuery;

        if (tags != null) {
            if (!countFlag) {
                sqlQuery = String.format(SELECT_BY_TAG, parseToString(tags), tags.length);
            } else {
                sqlQuery = String.format(SELECT_COUNT_BY_TAG, parseToString(tags));
            }
        } else {
            if (!countFlag) {
                sqlQuery = SELECT_ALL;
            } else {
                sqlQuery = SELECT_COUNT;
            }
        }
        if (name != null) {
            sqlQuery += String.format(SEARCH_BY_NAME_WITH_TAG, name);
        }
        if (description != null) {
            sqlQuery += String.format(SEARCH_BY_DESCRIPTION_WITH_TAG, description);
        }
        if (sortParam != null && !countFlag) {
            if (!sortParam.equals(NAME) && !sortParam.equals(DATE_OF_MODIFICATION)) {
                throw new InvalidRequestParameterException(SORT_PARAMETER_INCORRECT);
            } else {
                sqlQuery += String.format(ORDER_BY, sortParam);
            }
            if (!sortOrder.equals(ASC) && !sortOrder.equals(DESC)) {
                throw new InvalidRequestParameterException(SORT_ORDER_INCORRECT);
            } else {
                sqlQuery += sortOrder;
            }
        }

        if (!countFlag) {
            query = entityManager.createNativeQuery(sqlQuery, GiftCertificate.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit);
        } else {
            query = entityManager.createNativeQuery(sqlQuery);
        }

        return query;
    }

    private String parseToString(final String[] strings) {
        return Arrays.stream(strings)
                .map(string -> "'" + string + "'")
                .collect(Collectors.toList())
                .toString()
                .replaceAll("[\\[\\]]", "");
    }

}
