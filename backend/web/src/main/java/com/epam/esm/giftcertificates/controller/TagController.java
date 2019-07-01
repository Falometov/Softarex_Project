package com.epam.esm.giftcertificates.controller;

import com.epam.esm.giftcertificates.dto.PaginationDto;
import com.epam.esm.giftcertificates.dto.TagDto;
import com.epam.esm.giftcertificates.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for work with dto class {@link TagDto}.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(final TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Get method for select all objects {@link TagDto}.
     *
     * @param offset value of the pagination offset
     * @param limit  value of the pagination max data limit
     * @return list of objects {@link TagDto}
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<TagDto> findAll(@RequestParam(defaultValue = "0") final Integer offset,
                                @RequestParam(defaultValue = "10") final Integer limit) {
        return tagService.findAll(new PaginationDto(offset, limit));
    }

    /**
     * Get method for select object {@link TagDto} by id.
     *
     * @param id value of the object id
     * @return value of the object {@link ResponseEntity}
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<TagDto> findById(@PathVariable final Long id) {
        return new ResponseEntity<>(tagService.findById(id), HttpStatus.OK);
    }

    /**
     * Delete method for delete object {@link TagDto} by id.
     *
     * @param id value of the object id
     * @return value of the object {@link ResponseEntity}
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable final Long id) {
        tagService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Post method for create object {@link TagDto}.
     *
     * @param tag value of the object {@link TagDto}
     * @return value of the object {@link ResponseEntity}
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TagDto> create(@RequestBody TagDto tag) {
        return new ResponseEntity<>(tagService.add(tag), HttpStatus.OK);
    }

}
