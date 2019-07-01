package com.epam.esm.giftcertificates.repository;

import com.epam.esm.giftcertificates.entity.GiftCertificate;
import com.epam.esm.giftcertificates.entity.User;
import com.epam.esm.giftcertificates.repository.api.RepositoryApi;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;

/**
 * Repository interface for entity {@link User}.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
public interface UserRepository extends RepositoryApi<User> {
}
