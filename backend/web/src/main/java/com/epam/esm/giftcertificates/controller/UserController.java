package com.epam.esm.giftcertificates.controller;

import com.epam.esm.giftcertificates.dto.*;
import com.epam.esm.giftcertificates.service.CertificateService;
import com.epam.esm.giftcertificates.service.TagService;
import com.epam.esm.giftcertificates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for work with dto class {@link UserDto}.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private CertificateService certificateService;
    private TagService tagService;

    @Autowired
    public UserController(final UserService userService, final CertificateService certificateService,
                          final TagService tagService) {
        this.userService = userService;
        this.certificateService = certificateService;
        this.tagService = tagService;
    }

    /**
     * Get method for select all objects {@link UserDto}.
     *
     * @param username value of the username
     * @param offset   value of the pagination offset
     * @param limit    value of the pagination data max limit
     * @return list of objects {@link UserDto}
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> findAll(@RequestParam final Optional<String> username,
                                 @RequestParam(defaultValue = "0") final Integer offset,
                                 @RequestParam(defaultValue = "10") final Integer limit) {
        String name;
        List<UserDto> users;

        if (!username.isPresent()) {
            users = userService.findAll(new PaginationDto(offset, limit));
        } else {
            name = username.get();
            users = new ArrayList<UserDto>() {{
                add(userService.findByUsername(name));
            }};
        }

        return users;
    }

    /**
     * Get mapping for select object {@link GiftCertificateDto} for user.
     *
     * @param username value of the username
     * @param offset   value of the pagination offset position
     * @param limit    value of the pagination data max limit
     * @return value of the object {@link ResponseEntity}
     */
    @CrossOrigin
    @GetMapping("/{username}/certificates")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CertificatePageDto> findUserCertificates(@RequestParam(defaultValue = "0") final Integer offset,
                                                                   @RequestParam(defaultValue = "10") final Integer limit,
                                                                   @PathVariable final String username) {
        CertificatePageDto certificatePage = certificateService.findByUsername(username,
                new PaginationDto(offset, limit));
        return new ResponseEntity<>(certificatePage, HttpStatus.OK);
    }

    /**
     * Get mapping for select object {@link TagDto} by user id.
     *
     * @param id value of the user id
     * @return value of the object {@link ResponseEntity}
     */
    @GetMapping("/{id}/tags")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TagDto> findPopularTag(@PathVariable final Long id) {
        return new ResponseEntity<>(tagService.findByUserId(id), HttpStatus.OK);
    }

    /**
     * Get method for select object {@link UserDto} by id.
     *
     * @param id value of the object id
     * @return value of the object {@link ResponseEntity}
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> findById(@PathVariable final Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    /**
     * Delete method for remove object {@link UserDto}.
     *
     * @param id value of the object id
     * @return value of the object {@link ResponseEntity}
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable final Long id) {
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Post method for create object {@link UserDto}.
     *
     * @param user value of the object {@link UserDto}
     * @return value of the object {@link ResponseEntity}
     */
    @PostMapping
    @PreAuthorize("!hasRole('USER') and !hasRole('ADMIN')")
    public ResponseEntity<UserDto> create(@RequestBody final UserDto user) {
        return new ResponseEntity<>(userService.add(user), HttpStatus.OK);
    }

}
