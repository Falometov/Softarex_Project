package com.epam.esm.giftcertificates.service.impl;

import com.epam.esm.giftcertificates.annotation.Valid;
import com.epam.esm.giftcertificates.dto.PaginationDto;
import com.epam.esm.giftcertificates.dto.PurchaseCertificateDto;
import com.epam.esm.giftcertificates.dto.PurchaseDto;
import com.epam.esm.giftcertificates.entity.*;
import com.epam.esm.giftcertificates.exception.EntityNotFoundException;
import com.epam.esm.giftcertificates.exception.UserNotFoundException;
import com.epam.esm.giftcertificates.repository.CertificateRepository;
import com.epam.esm.giftcertificates.repository.PurchaseRepository;
import com.epam.esm.giftcertificates.repository.UserRepository;
import com.epam.esm.giftcertificates.service.PurchaseService;
import com.epam.esm.giftcertificates.specification.purchase.PurchaseSelectAll;
import com.epam.esm.giftcertificates.specification.user.SelectByUsername;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for repository {@link PurchaseRepository}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private static final String USER_NOT_FOUND_BY_USERNAME_KEY = "NotFound.user.by.username";
    private static final String CERTIFICATE_NOT_FOUND_KEY = "NotFound.certificate";
    private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);
    private UserRepository userRepository;
    private CertificateRepository certificateRepository;
    private PurchaseRepository purchaseRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PurchaseServiceImpl(final PurchaseRepository purchaseRepository,
                               final CertificateRepository certificaterepository,
                               final UserRepository userRepository,
                               final ModelMapper modelMapper) {
        this.purchaseRepository = purchaseRepository;
        this.certificateRepository = certificaterepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional
    public PurchaseDto add(final PurchaseDto purchaseDto) {
        logger.info("Method add(PurchaseDto) call.");
        User user = userRepository.query(new SelectByUsername(purchaseDto.getLogin(), null, null))
                .stream()
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_USERNAME_KEY, purchaseDto.getLogin()));

        if (CollectionUtils.isEmpty(purchaseDto.getCertificates())) {
            throw new EntityNotFoundException(CERTIFICATE_NOT_FOUND_KEY, null);
        }

        purchaseDto.setCertificates(mergeDuplicates(purchaseDto.getCertificates()));

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setTimestamp(LocalDateTime.now());
        purchase.setCost(new BigDecimal(0));

        purchaseRepository.create(purchase);

        purchase.setPurchaseCertificates(purchaseDto.getCertificates().stream().map(purCertificate -> {
            GiftCertificate certificate = certificateRepository.findById(
                    purCertificate.getCertificateId()).orElseThrow(
                    () -> new EntityNotFoundException(CERTIFICATE_NOT_FOUND_KEY, purCertificate.getCertificateId()));
            purchase.setCost(purchase.getCost().add(certificate.getPrice()
                    .multiply(new BigDecimal(purCertificate.getCount()))));

            CompositeId compositeId = new CompositeId();
            compositeId.setPurchaseId(purchase.getId());
            compositeId.setCertificateId(certificate.getId());

            PurchaseCertificate purchaseCertificate = new PurchaseCertificate();
            purchaseCertificate.setCompositeId(compositeId);
            purchaseCertificate.setPurchase(purchase);
            purchaseCertificate.setCount(purCertificate.getCount());
            purchaseCertificate.setGiftCertificate(certificate);
            purchaseCertificate.setPurchasePrice(certificate.getPrice());

            return purchaseCertificate;
        }).collect(Collectors.toList()));

        logger.info("Entity Purchase was created.");
        return modelMapper.map(purchaseRepository.create(purchase), PurchaseDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PurchaseDto findById(Long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional(readOnly = true)
    public List<PurchaseDto> findAll(final PaginationDto pagination) {
        logger.info("Method findAll(PaginationDto) call.");
        return purchaseRepository.query(new PurchaseSelectAll(pagination.getOffset(), pagination.getLimit()))
                .stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseDto.class))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional(readOnly = true)
    public List<PurchaseDto> findByUsername(final String username, final PaginationDto pagination) {
        logger.info("Method findByUsername(String, PaginationDto) call.");
        return userRepository.query(new SelectByUsername(username, pagination.getOffset(), pagination.getLimit()))
                .stream()
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_USERNAME_KEY, username))
                .getPurchases()
                .stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseDto.class))
                .collect(Collectors.toList());
    }

    private List<PurchaseCertificateDto> mergeDuplicates(final List<PurchaseCertificateDto> purchaseCertificates) {
        List<PurchaseCertificateDto> certificates = new ArrayList<>();

        purchaseCertificates.forEach(purchaseCertificate -> {
            if (certificates.stream().anyMatch(
                    certificate -> certificate.getCertificateId().equals(purchaseCertificate.getCertificateId()))) {
                certificates.forEach(certificate -> {
                    if (certificate.getCertificateId().equals(purchaseCertificate.getCertificateId())) {
                        certificate.setCount(certificate.getCount() + purchaseCertificate.getCount());
                    }
                });
            } else {
                certificates.add(purchaseCertificate);
            }
        });
        return certificates;
    }

}
