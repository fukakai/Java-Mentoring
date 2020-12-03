package com.company.omatfat.poc.metier.service.impl;

import com.company.omatfat.poc.metier.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.company.omatfat.poc.metier.service.api.UserServiceApi;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Romain DALICHAMP - romain.dalichamp@alithya.com
 * <p>
 */
@SpringBootTest
class UserServiceTest {
    /*
        /!\ ----- Test name = ClassNameTest                                /!\
        /!\ ----- SERVICES are the only revelent classes to be Unit Test   /!\
        /!\ ----- PUBLIC Methods are to test ONLY                          /!\
     */

    @Autowired
    UserServiceApi userService;

    @Test
    @DisplayName("GIVEN fake UserEntity WHEN i get by id THEN i expect the correct infos")
    void addAndGetUserEntityTest() {
        // GIVEN - Fake Data
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Anthony");
        userEntity.setLastName("Stark");
        userEntity.setOld(50);
        UserEntity myUserEntityCreated = userService.addUserEntity(userEntity);

        // WHEN - method to test
        Optional<UserEntity> myResult = userService.getUserEntity(
                myUserEntityCreated.getId()
        );

        // THEN - Expected Result
        Assertions.assertTrue(myResult.get().getFirstName().equals("Anthony"));
        Assertions.assertTrue(myResult.get().getLastName().equals("Stark"));
        Assertions.assertTrue(myResult.get().getOld().equals(50));

        // FINALLY - clean
        userService.deleteUserEntity(myUserEntityCreated.getId());
    }

    @Test
    @DisplayName("GIVEN fake List of UserEntity WHEN i get all THEN i expect a list containing theses users")
    void getUserEntityListTest() {
        // GIVEN - Fake Data
        UserEntity userEntityOne = new UserEntity();
        userEntityOne.setFirstName("Anthony");
        userEntityOne.setLastName("Stark");
        userEntityOne.setOld(50);

        UserEntity userEntityTwo = new UserEntity();
        userEntityTwo.setFirstName("Anthony");
        userEntityTwo.setLastName("Stark");
        userEntityTwo.setOld(50);

        List<UserEntity> myUserEntityList = new ArrayList<>();
        myUserEntityList.add(userEntityOne);
        myUserEntityList.add(userEntityTwo);
        UserEntity myUserEntityCreatedOne = userService.addUserEntity(userEntityOne);
        UserEntity myUserEntityCreatedTwo = userService.addUserEntity(userEntityTwo);

        // WHEN - method to test
        List<UserEntity> myResult = userService.getAllUserEntity();

        // THEN - Expected Result
        Assertions.assertTrue(myResult.contains(myUserEntityCreatedOne));
        Assertions.assertTrue(myResult.contains(myUserEntityCreatedTwo));

        // FINALLY - clean database
        userService.deleteUserEntity(myUserEntityCreatedOne.getId());
        userService.deleteUserEntity(myUserEntityCreatedTwo.getId());
    }

    @Test
    @DisplayName("GIVEN a UserEntity WHEN i get update it THEN the updated User is saved in database")
    void updateUserEntityTest() {
        // GIVEN - Fake Data
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Anthony");
        userEntity.setLastName("Stark");
        userEntity.setOld(50);

        // WHEN- create
        UserEntity myUserDtoCreated = userService.addUserEntity(userEntity);

        // THEN
        Assertions.assertTrue(myUserDtoCreated.getFirstName().equals("Anthony"));

        // WHEN - update
        userEntity.setFirstName("Tony");
        try {
            UserEntity myUserDtoUpdated = userService.updateUserEntity(userEntity);
        }catch(Exception e){
            System.out.println(e);
        }
        // THEN
        Optional<UserEntity> myUpdatedResult =
                userService.getUserEntity(myUserDtoCreated.getId());
        Assertions.assertTrue(myUpdatedResult.get().getFirstName().equals("Tony"));

        // FINALLY - clean database
        userService.deleteUserEntity(myUserDtoCreated.getId());
    }

    @Test
    @DisplayName("GIVEN a UserEntity WHEN i save and delete it THEN it is not present in database")
    void deleteUserEntityTest() {
        // GIVEN - Fake Data
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Anthony");
        userEntity.setLastName("Stark");
        userEntity.setOld(50);

        // WHEN- create
        UserEntity myUserEntityCreated = userService.addUserEntity(userEntity);

        // THEN
        Assertions.assertTrue(myUserEntityCreated.getFirstName().equals("Anthony"));

        // WHEN - delete
        userService.deleteUserEntity(myUserEntityCreated.getId());

        // THEN
        Optional<UserEntity> myUpdatedResult =
                userService.getUserEntity(myUserEntityCreated.getId());
        Assertions.assertTrue(!myUpdatedResult.isPresent());
    }
}
