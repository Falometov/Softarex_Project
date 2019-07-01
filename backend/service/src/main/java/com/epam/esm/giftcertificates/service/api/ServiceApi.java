package com.epam.esm.giftcertificates.service.api;

import com.epam.esm.giftcertificates.dto.PaginationDto;

import java.util.List;

/**
 * Service interface with basic methods for work with repository.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public interface ServiceApi<T> {

    /**
     * This method add entity object.
     *
     * @param object value of the entity object
     * @return entity object
     */
    T add(T object);

    /**
     * This method remove entity object.
     *
     * @param id value of the entity id
     */
    void remove(Long id);

    /**
     * This method return entity object by id.
     *
     * @param id value of the entity id
     * @return entity object
     */
    T findById(Long id);

    /**
     * This method return all entity objects.
     *
     * @param pagination value of the object {@link PaginationDto}
     * @return list of the entity objects
     */
    List<T> findAll(PaginationDto pagination);

}
