package com.epam.esm.giftcertificates.service.impl;

import com.epam.esm.giftcertificates.annotation.Valid;
import com.epam.esm.giftcertificates.dto.PaginationDto;
import com.epam.esm.giftcertificates.dto.TagDto;
import com.epam.esm.giftcertificates.entity.Tag;
import com.epam.esm.giftcertificates.exception.EntityAlreadyExistException;
import com.epam.esm.giftcertificates.exception.EntityNotFoundException;
import com.epam.esm.giftcertificates.repository.TagRepository;
import com.epam.esm.giftcertificates.repository.UserRepository;
import com.epam.esm.giftcertificates.repository.impl.TagRepositoryImpl;
import com.epam.esm.giftcertificates.service.TagService;
import com.epam.esm.giftcertificates.specification.tag.SelectByName;
import com.epam.esm.giftcertificates.specification.tag.TagSelectAll;
import com.epam.esm.giftcertificates.specification.tag.TagSelectByUserId;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for repository {@link TagRepositoryImpl}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
@Service
public class TagServiceImpl implements TagService {

    private static final String USER_NOT_FOUND_KEY = "NotFound.user";
    private static final String TAG_NOT_FOUND_KEY = "NotFound.tag";
    private static final String TAG_ALREADY_EXIST_KEY = "Exist.tag";
    private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);
    private UserRepository userRepository;
    private TagRepository tagRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TagServiceImpl(final TagRepository tagRepository, final UserRepository userRepository,
                          final ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional
    public TagDto add(final TagDto tagDto) {
        logger.info("Method add(TagDto) call.");
        Tag tag = modelMapper.map(tagDto, Tag.class);
        if (!CollectionUtils.isEmpty(tagRepository.query(new SelectByName(tag.getName())))) {
            throw new EntityAlreadyExistException(TAG_ALREADY_EXIST_KEY, tag.getName());
        }
        logger.info("Entity Tag created.");
        return modelMapper.map(tagRepository.create(tag), TagDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void remove(final Long id) {
        logger.info("Method remove(Long) call.");
        Tag tag = modelMapper.map(findById(id), Tag.class);
        tagRepository.delete(tag);
        logger.info("Entity Tag was removed by id " + id + ".");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public TagDto findById(final Long id) {
        logger.info("Method findById(Long) call.");
        Tag tag = tagRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(TAG_NOT_FOUND_KEY, id));
        return modelMapper.map(tag, TagDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional(readOnly = true)
    public List<TagDto> findAll(final PaginationDto pagination) {
        logger.info("Method findAll(PaginationDto) call.");
        return tagRepository.query(new TagSelectAll(pagination.getOffset(), pagination.getLimit()))
                .stream()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public TagDto findByUserId(final Long userId) {
        logger.info("Method findByUsername(Long) call.");
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_KEY, userId));
        Tag tag = tagRepository.query(new TagSelectByUserId(userId)).stream().findFirst().orElse(null);
        return tag != null ? modelMapper.map(tag, TagDto.class) : null;
    }

}
