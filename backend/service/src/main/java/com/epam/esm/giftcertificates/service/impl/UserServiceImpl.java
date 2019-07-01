package com.epam.esm.giftcertificates.service.impl;

import com.epam.esm.giftcertificates.annotation.Valid;
import com.epam.esm.giftcertificates.dto.PaginationDto;
import com.epam.esm.giftcertificates.dto.UserDto;
import com.epam.esm.giftcertificates.entity.User;
import com.epam.esm.giftcertificates.entity.enumeration.UserRole;
import com.epam.esm.giftcertificates.exception.EntityAlreadyExistException;
import com.epam.esm.giftcertificates.exception.EntityNotFoundException;
import com.epam.esm.giftcertificates.exception.UserNotFoundException;
import com.epam.esm.giftcertificates.repository.UserRepository;
import com.epam.esm.giftcertificates.repository.impl.UserRepositoryImpl;
import com.epam.esm.giftcertificates.service.UserService;
import com.epam.esm.giftcertificates.specification.user.SelectByUsername;
import com.epam.esm.giftcertificates.specification.user.UserSelectAll;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for repository {@link UserRepositoryImpl}.
 *
 * @author Mikita_Ustsiushenka
 * @version 1.0
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final String USER_NOT_FOUND_BY_USERNAME_KEY = "NotFound.user.by.username";
    private static final String USER_NOT_FOUND_KEY = "NotFound.user";
    private static final String USER_ALREADY_EXIST_KEY = "Exist.user";
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final ModelMapper modelMapper,
                           final PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.query(new SelectByUsername(username, null, null))
                .stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole().name())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional
    public UserDto add(final UserDto userDto) {
        logger.info("Method add(UserDto) call.");
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setUserRole(UserRole.ROLE_USER);

        if (!CollectionUtils.isEmpty(userRepository.query(
                new SelectByUsername(user.getUsername(), null, null)))) {
            throw new EntityAlreadyExistException(USER_ALREADY_EXIST_KEY, user.getUsername());
        }

        logger.info("Entity User created.");
        return modelMapper.map(userRepository.create(user), UserDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void remove(final Long id) {
        logger.info("Method remove(Long) call.");
        User user = modelMapper.map(findById(id), User.class);
        userRepository.delete(user);
        logger.info("Entity User was removed by id " + id + ".");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserDto findById(final Long id) {
        logger.info("Method findById(Long) call.");
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(USER_NOT_FOUND_KEY, id));
        return modelMapper.map(user, UserDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Valid
    @Transactional(readOnly = true)
    public List<UserDto> findAll(final PaginationDto pagination) {
        logger.info("Method findAll() call.");
        return userRepository.query(new UserSelectAll(pagination.getOffset(), pagination.getLimit()))
                .stream()
                .map(tag -> modelMapper.map(tag, UserDto.class))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserDto findByUsername(final String username) {
        logger.info("Method findByUsername(String) call.");
        return userRepository.query(new SelectByUsername(username, null, null))
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_USERNAME_KEY, username));
    }

}
