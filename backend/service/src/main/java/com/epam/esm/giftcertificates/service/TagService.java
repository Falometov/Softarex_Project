package com.epam.esm.giftcertificates.service;

import com.epam.esm.giftcertificates.dto.TagDto;
import com.epam.esm.giftcertificates.entity.Tag;
import com.epam.esm.giftcertificates.service.api.ServiceApi;

/**
 * Service interface for entity {@link Tag}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
public interface TagService extends ServiceApi<TagDto> {

    /**
     * This method return popular tag by user id.
     *
     * @param userId value of the user id
     * @return value of the object {@link TagDto}
     */
    TagDto findByUserId(Long userId);

}