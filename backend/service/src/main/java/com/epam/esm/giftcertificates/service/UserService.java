package com.epam.esm.giftcertificates.service;

import com.epam.esm.giftcertificates.dto.GiftCertificateDto;
import com.epam.esm.giftcertificates.dto.PaginationDto;
import com.epam.esm.giftcertificates.dto.UserDto;
import com.epam.esm.giftcertificates.entity.User;
import com.epam.esm.giftcertificates.service.api.ServiceApi;

import java.util.List;

/**
 * Service interface for entity {@link User}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public interface UserService extends ServiceApi<UserDto> {

    /**
     * This method find user by username.
     *
     * @param username value of the username
     * @return value of the object {@link UserDto}
     */
    UserDto findByUsername(String username);

}
