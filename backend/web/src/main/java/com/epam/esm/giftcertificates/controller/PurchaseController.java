package com.epam.esm.giftcertificates.controller;

import com.epam.esm.giftcertificates.dto.PaginationDto;
import com.epam.esm.giftcertificates.dto.PurchaseDto;
import com.epam.esm.giftcertificates.entity.enumeration.UserRole;
import com.epam.esm.giftcertificates.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for work with dto class {@link PurchaseDto}.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(final PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * Get method for select all objects {@link PurchaseDto}.
     *
     * @param authentication value of the object {@link Authentication}
     * @param offset           value of the pagination offset
     * @param limit           value of the pagination max data limit
     * @return list of the objects {@link PurchaseDto}
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<PurchaseDto> findAll(@RequestParam(defaultValue = "0") final Integer offset,
                                     @RequestParam(defaultValue = "10") final Integer limit,
                                     final Authentication authentication) {
        String role = authentication.getAuthorities().toString().replaceAll("[\\]\\[]", "");
        List<PurchaseDto> purchases;
        PaginationDto pagination = new PaginationDto(offset, limit);

        if (role.equals(UserRole.ROLE_USER.name())) {
            purchases = purchaseService.findByUsername(authentication.getName(), pagination);
        } else {
            purchases = purchaseService.findAll(pagination);
        }
        return purchases;
    }

    /**
     * Post method for create object {@link PurchaseDto}.
     *
     * @param purchase       value of the object {@link PurchaseDto}
     * @param authentication value of the object {@link Authentication}
     * @return value of the object {@link ResponseEntity}
     */
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PurchaseDto> create(@RequestBody final PurchaseDto purchase,
                                              final Authentication authentication) {
        purchase.setLogin(authentication.getName());
        return new ResponseEntity<>(purchaseService.add(purchase), HttpStatus.OK);
    }

}
