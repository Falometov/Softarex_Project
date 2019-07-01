package com.epam.esm.giftcertificates.repository.api;

import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface with CRUD declaration of methods.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
public interface RepositoryApi<T> {

    /**
     * This method add entity object in database.
     *
     * @param object value of the entity object
     * @return value of the entity
     */
    T create(T object);

    /**
     * This method delete entity object from database.
     *
     * @param object value of the entity object
     */
    void delete(T object);

    /**
     * This method find entity object by id.
     *
     * @param id value of the entity id
     * @return value of the entity object
     */
    Optional<T> findById(Long id);

    /**
     * This method return entity objects from database by some specification.
     *
     * @param specification value of the specification
     * @return list of entity objects
     */
    List<T> query(SqlSpecification<T> specification);

}
