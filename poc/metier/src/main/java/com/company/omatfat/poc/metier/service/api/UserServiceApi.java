package com.company.omatfat.poc.metier.service.api;

import com.company.omatfat.poc.metier.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Romain DALICHAMP - romain.dalichamp@alithya.com
 * <p>
 */
public interface UserServiceApi {

    Optional<UserEntity> getUserEntity(Long id);

    List<UserEntity> getTwoUserEntity(Long id, Long id2);

    List<UserEntity> getAllUserEntity();

    UserEntity addUserEntity(UserEntity metierEntity);

    UserEntity updateUserEntity(UserEntity metierEntity);

    void deleteUserEntity(Long id);
}
