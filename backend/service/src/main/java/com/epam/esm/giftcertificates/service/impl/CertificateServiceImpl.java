package com.epam.esm.giftcertificates.service.impl;

import com.epam.esm.giftcertificates.annotation.Valid;
import com.epam.esm.giftcertificates.dto.*;
import com.epam.esm.giftcertificates.entity.GiftCertificate;
import com.epam.esm.giftcertificates.entity.Tag;
import com.epam.esm.giftcertificates.exception.EntityNotFoundException;
import com.epam.esm.giftcertificates.exception.UserNotFoundException;
import com.epam.esm.giftcertificates.repository.CertificateRepository;
import com.epam.esm.giftcertificates.repository.TagRepository;
import com.epam.esm.giftcertificates.repository.UserRepository;
import com.epam.esm.giftcertificates.repository.impl.CertificateRepositoryImpl;
import com.epam.esm.giftcertificates.service.CertificateService;
import com.epam.esm.giftcertificates.specification.giftcertificate.CertificateSelectAll;
import com.epam.esm.giftcertificates.specification.giftcertificate.CertificateSelectByUsername;
import com.epam.esm.giftcertificates.specification.giftcertificate.SelectBySpecification;
import com.epam.esm.giftcertificates.specification.tag.SelectByName;
import com.epam.esm.giftcertificates.specification.user.SelectByUsername;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for repository {@link CertificateRepositoryImpl}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
@Service
public class CertificateServiceImpl implements CertificateService {

    private static final String CERTIFICATE_NOT_FOUND_KEY = "NotFound.certificate";
    private static final String USER_NOT_FOUND_KEY = "NotFound.user";
    private static final Logger logger = LoggerFactory.getLogger(CertificateServiceImpl.class);
    private UserRepository userRepository;
    private CertificateRepository certificateRepository;
    private TagRepository tagRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CertificateServiceImpl(final CertificateRepository certificateRepository,
                                  final UserRepository userRepository, final TagRepository tagRepository,
                                  final ModelMapper modelMapper) {
        this.certificateRepository = certificateRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional
    public GiftCertificateDto add(final GiftCertificateDto giftCertificateDto) {
        logger.info("Method add(GiftCertificateDto) call.");
        GiftCertificate certificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
        certificate.setDateOfCreation(LocalDateTime.now());
        certificate.setDateOfModification(LocalDateTime.now());
        certificate.setTags(addTags(certificate.getTags()));
        certificate.setActive(true);
        logger.info("Entity GiftCertificate was created.");
        return modelMapper.map(certificateRepository.create(certificate), GiftCertificateDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional
    public GiftCertificateDto update(final GiftCertificateDto giftCertificateDto) {
        logger.info("Method update(GiftCertificateDto) call.");
        giftCertificateDto.setDateOfCreation(findById(giftCertificateDto.getId()).getDateOfCreation());

        GiftCertificate certificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
        certificate.setDateOfModification(LocalDateTime.now());
        certificate.setTags(addTags(certificate.getTags()));
        certificate.setActive(true);
        logger.info("Entity GiftCertificate with id " + certificate.getId() + " was updated.");
        return modelMapper.map(certificateRepository.update(certificate), GiftCertificateDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional
    public GiftCertificateDto update(final PriceDto priceDto) {
        logger.info("Method update(PriceDto) call.");
        GiftCertificate certificate = modelMapper.map(findById(priceDto.getId()), GiftCertificate.class);
        certificate.setPrice(priceDto.getPrice());
        certificate.setDateOfModification(LocalDateTime.now());
        certificate.setActive(true);
        logger.info("GiftCertificate price with id " + priceDto.getId() + " was updated.");
        return modelMapper.map(certificateRepository.update(certificate), GiftCertificateDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void remove(final Long id) {
        logger.info("Method remove(Long) call.");
        GiftCertificate certificate = modelMapper.map(findById(id), GiftCertificate.class);
        certificate.setActive(false);
        certificateRepository.update(certificate);
        logger.info("Entity GiftCertificate was removed by id " + id + ".");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public GiftCertificateDto findById(final Long id) {
        logger.info("Method findById(Long) call.");
        GiftCertificate giftCertificate = certificateRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(CERTIFICATE_NOT_FOUND_KEY, id));
        return modelMapper.map(giftCertificate, GiftCertificateDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional(readOnly = true)
    public List<GiftCertificateDto> findAll(final PaginationDto pagination) {
        logger.info("Method findAll(PaginationDto) call.");
        return certificateRepository.query(new CertificateSelectAll(pagination.getOffset(), pagination.getLimit()))
                .stream()
                .map(certificate -> modelMapper.map(certificate, GiftCertificateDto.class))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional(readOnly = true)
    public CertificatePageDto findBySpecification(final SpecificationDto specification,
                                                  final PaginationDto pagination) {
        logger.info("Method findBySpecification(SpecificationDto, PaginationDto) call.");
        List<GiftCertificateDto> certificates;
        Long count;

        certificates = certificateRepository.query(new SelectBySpecification(specification.getTags(), specification.getName(),
                specification.getDescription(), specification.getSortParam(), specification.getSortOrder(),
                pagination.getOffset(), pagination.getLimit(), false))
                .stream()
                .map(certificate -> modelMapper.map(certificate, GiftCertificateDto.class))
                .collect(Collectors.toList());
        count = certificateRepository.getCertificatesCount(new SelectBySpecification(specification.getTags(),
                specification.getName(), specification.getDescription(), specification.getSortParam(),
                specification.getSortOrder(), null, null, true));
        return new CertificatePageDto(certificates,
                new Double(Math.ceil(count.doubleValue() / pagination.getLimit().doubleValue())).longValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Long getCertificatesCount(final SpecificationDto specification) {
        logger.info("Method getCertificatesCount(SpecificationDto) call.");
        return certificateRepository.getCertificatesCount(new SelectBySpecification(specification.getTags(),
                specification.getName(), specification.getDescription(), specification.getSortParam(),
                specification.getSortOrder(), null, null, true));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional(readOnly = true)
    public CertificatePageDto findByUsername(final String username, final PaginationDto pagination) {
        logger.info("Method findByUsername(Long, PaginationDto) call.");
        List<GiftCertificateDto> certificates;
        Long count;

        userRepository.query(new SelectByUsername(username, 0, 1)).stream().findFirst().orElseThrow(
                () -> new UserNotFoundException(USER_NOT_FOUND_KEY, username));
        certificates = certificateRepository.query(new CertificateSelectByUsername(username,
                pagination.getOffset(), pagination.getLimit(), false))
                .stream()
                .map(certificate -> modelMapper.map(certificate, GiftCertificateDto.class))
                .collect(Collectors.toList());
        count = certificateRepository.getCertificatesCount(new CertificateSelectByUsername(username,
                null, null, true));
        return new CertificatePageDto(certificates,
                new Double(Math.ceil(count.doubleValue() / pagination.getLimit().doubleValue())).longValue());
    }

    /**
     * This method get or add and get certificate tags.
     *
     * @param tags list of the objects {@link Tag}
     * @return list of objects {@link }
     */
    private List<Tag> addTags(final List<Tag> tags) {
        Object[] tagNames;
        List<Tag> certificateTags;

        if (tags != null) {
            tagNames = tags.stream().map(Tag::getName).toArray();
            certificateTags = new ArrayList<>(tagRepository.query(new SelectByName(tagNames)));
            tags
                    .stream()
                    .filter(tag -> certificateTags
                            .stream()
                            .noneMatch(certificateTag -> certificateTag.getName().equals(tag.getName())))
                    .map(tagRepository::create)
                    .forEach(certificateTags::add);
        } else {
            certificateTags = new ArrayList<>();
        }

        return certificateTags;
    }

}
