package com.epam.esm.giftcertificates.controller;

import com.epam.esm.giftcertificates.dto.*;
import com.epam.esm.giftcertificates.entity.GiftCertificate;
import com.epam.esm.giftcertificates.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for work with entity class {@link GiftCertificate}.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {

    private CertificateService certificateService;

    @Autowired
    public GiftCertificateController(final CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * Get method for select all objects {@link GiftCertificateDto}.
     *
     * @param tags        array of the tag names
     * @param name        value of the gift certificate name
     * @param description value of the gift certificate description
     * @param sortParam   value of the sortParam parameter
     * @param sortOrder   value of the asc or desc
     * @param offset      value of the pagination offset
     * @param limit       value of the pagination max data offset
     * @return list of objects {@link GiftCertificateDto}
     */
    @CrossOrigin
    @GetMapping
    public ResponseEntity<CertificatePageDto> findAll(@RequestParam final Optional<String[]> tags,
                                                      @RequestParam final Optional<String> name,
                                                      @RequestParam final Optional<String> description,
                                                      @RequestParam(value = "sort_param",
                                                              defaultValue = "date_of_modification") final String sortParam,
                                                      @RequestParam(value = "sort_order",
                                                              defaultValue = "desc") final String sortOrder,
                                                      @RequestParam(defaultValue = "0") final Integer offset,
                                                      @RequestParam(defaultValue = "10") final Integer limit) {
        Long pages;
        CertificatePageDto certificatePage;
        PaginationDto pagination = new PaginationDto(offset, limit);

        SpecificationDto specification = new SpecificationDto();
        specification.setTags(tags.orElse(null));
        specification.setName(name.orElse(null));
        specification.setDescription(description.orElse(null));
        specification.setSortParam(sortParam);
        specification.setSortOrder(sortOrder);

        certificatePage = certificateService.findBySpecification(specification, pagination);
        return new ResponseEntity<>(certificatePage, HttpStatus.OK);
    }

    /**
     * Get method for select object {@link GiftCertificateDto} by id.
     *
     * @param id value of the object id
     * @return value of the object {@link ResponseEntity}
     */
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<GiftCertificateDto> findById(@PathVariable final Long id) {
        return new ResponseEntity<>(certificateService.findById(id), HttpStatus.OK);
    }

    /**
     * Delete method for delete object {@link GiftCertificateDto} by id.
     *
     * @param id value of the object id
     * @return value of the object {@link ResponseEntity}
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable final Long id) {
        certificateService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Put method for update object {@link GiftCertificateDto} by id.
     *
     * @param id                 value of the object id
     * @param giftCertificateDto value of the object {@link GiftCertificateDto}
     * @return value of the object {@link ResponseEntity}
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GiftCertificateDto> update(@PathVariable final Long id,
                                                     @RequestBody final GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.setId(id);
        return new ResponseEntity<>(certificateService.update(giftCertificateDto), HttpStatus.OK);
    }

    /**
     * Patch method for update price object {@link GiftCertificateDto}.
     *
     * @param id       value of the object id
     * @param priceDto value of the object {@link PriceDto}
     * @return value of the object {@link ResponseEntity}
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GiftCertificateDto> updateCertificatePrice(@PathVariable final Long id,
                                                                     @RequestBody PriceDto priceDto) {
        priceDto.setId(id);
        return new ResponseEntity<>(certificateService.update(priceDto), HttpStatus.OK);
    }

    /**
     * Post method for create object {@link GiftCertificateDto}.
     *
     * @param giftCertificateDto value of the object {@link GiftCertificateDto}
     * @return new value of the object {@link ResponseEntity}
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GiftCertificateDto> create(@RequestBody final GiftCertificateDto giftCertificateDto) {
        return new ResponseEntity<>(certificateService.add(giftCertificateDto), HttpStatus.OK);
    }

}
