package com.epam.esm.giftcertificates.repository.impl;

import com.epam.esm.giftcertificates.entity.GiftCertificate;
import com.epam.esm.giftcertificates.entity.User;
import com.epam.esm.giftcertificates.repository.UserRepository;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for entity {@link User}.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public User create(final User user) {
        entityManager.persist(user);
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final User user) {
        entityManager.remove(entityManager.find(User.class, user.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> findById(final Long id) {
        User user = entityManager.find(User.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> query(final SqlSpecification<User> sqlSpecification) {
        return sqlSpecification.buildQuery(entityManager).getResultList();
    }

}
