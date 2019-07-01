package com.epam.esm.giftcertificates.repository.impl;

import com.epam.esm.giftcertificates.entity.Tag;
import com.epam.esm.giftcertificates.repository.TagRepository;
import com.epam.esm.giftcertificates.specification.api.SqlSpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for entity {@link Tag}.
 *
 * @author Vadim_Orol
 * @version 1.0
 */
@Repository
public class TagRepositoryImpl implements TagRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public Tag create(final Tag tag) {
        entityManager.persist(tag);
        return tag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Tag tag) {
        entityManager.remove(entityManager.find(Tag.class, tag.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Tag> findById(final Long id) {
        Tag tag = entityManager.find(Tag.class, id);
        return tag != null ? Optional.of(tag) : Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tag> query(final SqlSpecification<Tag> sqlSpecification) {
        return sqlSpecification.buildQuery(entityManager).getResultList();
    }

}
