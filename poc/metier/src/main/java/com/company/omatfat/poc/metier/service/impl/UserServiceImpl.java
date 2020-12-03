package com.company.omatfat.poc.metier.service.impl;

import com.company.omatfat.poc.metier.entity.UserEntity;
import com.company.omatfat.poc.metier.repository.UserRepository;
import com.company.omatfat.poc.metier.service.api.UserServiceApi;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author Romain DALICHAMP - romain.dalichamp@alithya.com
 * <p>
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserServiceApi {

    //@Autowired est remplacé par ==> @RequiredArgsConstructor de Lombok
    private final UserRepository userRepository;

    public Optional<UserEntity> getUserEntity(Long id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> getTwoUserEntity(Long id, Long id2) {
        Long[] listId = {id, id2};
        return userRepository.findAllById(Arrays.asList(listId));
    }

    public List<UserEntity> getAllUserEntity() {
        log.info("get alls");
        // Business rules
        return userRepository.findAll();
    }

    public UserEntity addUserEntity(UserEntity userEntity) {
        log.info("add");
        // Business rules
        return userRepository.save(userEntity);
    }

    public UserEntity updateUserEntity(UserEntity userEntity) {
        log.info("update");
        // Business rules
        return userRepository.save(userEntity);
    }

    public void deleteUserEntity(Long id) {
        log.info("deleteById");
        // Business rules
        userRepository.deleteById(id);
    }

}
