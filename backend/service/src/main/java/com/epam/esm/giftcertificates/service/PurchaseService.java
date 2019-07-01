package com.epam.esm.giftcertificates.service;

import com.epam.esm.giftcertificates.dto.PaginationDto;
import com.epam.esm.giftcertificates.dto.PurchaseDto;
import com.epam.esm.giftcertificates.entity.Purchase;
import com.epam.esm.giftcertificates.service.api.ServiceApi;

import java.util.List;

/**
 * Service interface for entity {@link Purchase}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public interface PurchaseService extends ServiceApi<PurchaseDto> {

    /**
     * Find user purchases by username.
     *
     * @param username   value of the username
     * @param pagination value of the object {@link PaginationDto}
     * @return list of the objects {@link PurchaseDto}
     */
    List<PurchaseDto> findByUsername(String username, PaginationDto pagination);

}
