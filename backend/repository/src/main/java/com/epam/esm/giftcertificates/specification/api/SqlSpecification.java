package com.epam.esm.giftcertificates.specification.api;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * This interface store method declaration for sql specification.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
public interface SqlSpecification<T> {

    /**
     * This method build sql query by some parameter.
     *
     * @param entityManager value of the object {@link EntityManager}
     * @return value of the sql query
     */
    Query buildQuery(EntityManager entityManager);

}
