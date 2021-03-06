package com.company.omatfat.poc.interaction.service.impl;

import com.company.omatfat.poc.interaction.dto.UserDto;
import com.company.omatfat.poc.interaction.exception.UserException;
import com.company.omatfat.poc.interaction.service.api.UserServiceApi;
import java.util.ArrayList;
import java.util.List;
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
public class UserServiceTest {
    /*
        /!\ ----- Test name = ClassNameTest                                /!\
        /!\ ----- SERVICES are the only revelent classes to be Unit Test   /!\
        /!\ ----- PUBLIC Methods are to test ONLY                          /!\
     */

    @Autowired
    UserServiceApi userService;

    @Test
    @DisplayName("GIVEN fake UserDto WHEN i get by id THEN i expect the correct infos")
    void addAndGetUserDtoTest() throws Exception {
        // GIVEN - Fake Data
        UserDto userEntity = new UserDto();
        userEntity.setFirstName("Anthony");
        userEntity.setLastName("Stark");
        userEntity.setOld(50);
        UserDto myUserDtoCreated = userService.addUserDto(userEntity);

        // WHEN - method to test
        UserDto myResult = userService.getUserDto(
                myUserDtoCreated.getId()
        );

        // THEN - Expected Result
        Assertions.assertTrue(myResult.getFirstName().equals("Anthony"));
        Assertions.assertTrue(myResult.getLastName().equals("Stark"));
        Assertions.assertTrue(myResult.getOld().equals(50));

        // FINALLY - clean
        userService.deleteUserDto(myUserDtoCreated.getId());
    }

    @Test
    @DisplayName("GIVEN fake List of UserDto WHEN i get all THEN i expect a list containing theses users")
    void getUserDtoListTest() throws Exception {
        // GIVEN - Fake Data
        UserDto userEntityOne = new UserDto();
        userEntityOne.setFirstName("Anthony");
        userEntityOne.setLastName("Stark");
        userEntityOne.setOld(50);

        UserDto userEntityTwo = new UserDto();
        userEntityTwo.setFirstName("Anthony");
        userEntityTwo.setLastName("Stark");
        userEntityTwo.setOld(50);

        List<UserDto> myUserDtoList = new ArrayList<>();
        myUserDtoList.add(userEntityOne);
        myUserDtoList.add(userEntityTwo);
        UserDto myUserDtoCreatedOne = userService.addUserDto(userEntityOne);
        UserDto myUserDtoCreatedTwo = userService.addUserDto(userEntityTwo);

        // WHEN - method to test
        List<UserDto> myResult = userService.getAllUserDto();

        // THEN - Expected Result
        Assertions.assertTrue(myResult.contains(myUserDtoCreatedOne));
        Assertions.assertTrue(myResult.contains(myUserDtoCreatedTwo));

        // FINALLY - clean database
        userService.deleteUserDto(myUserDtoCreatedOne.getId());
        userService.deleteUserDto(myUserDtoCreatedTwo.getId());
    }

    @Test
    @DisplayName("GIVEN a UserDto WHEN i get update it THEN the updated User is saved in database")
    void updateUserDtoTest() throws Exception {
        // GIVEN - Fake Data
        UserDto userDto = new UserDto();
        userDto.setFirstName("Anthony");
        userDto.setLastName("Stark");
        userDto.setOld(50);

        // WHEN- create
        UserDto myUserDtoCreated = userService.addUserDto(userDto);

        // THEN
        Assertions.assertTrue(myUserDtoCreated.getFirstName().equals("Anthony"));

        // WHEN - update
        myUserDtoCreated.setFirstName("Tony");
        UserDto myUserDtoUpdated = userService.updateUserDto(myUserDtoCreated);
        Assertions.assertNotNull(myUserDtoUpdated);

        // THEN
        UserDto myUpdatedResult =
                userService.getUserDto(myUserDtoCreated.getId());
        Assertions.assertTrue(myUpdatedResult.getFirstName().equals("Tony"));

        // FINALLY - clean database
        userService.deleteUserDto(myUserDtoCreated.getId());
    }

    @Test
    @DisplayName("GIVEN a UserDto WHEN i save and delete it THEN it is not present in database")
    void deleteUserDtoTest() throws Exception {
        // GIVEN - Fake Data
        UserDto userEntity = new UserDto();
        userEntity.setFirstName("Anthony");
        userEntity.setLastName("Stark");
        userEntity.setOld(50);

        // WHEN- create
        UserDto myUserDtoCreated = userService.addUserDto(userEntity);

        // THEN
        Assertions.assertTrue(myUserDtoCreated.getFirstName().equals("Anthony"));

        // WHEN - delete
        userService.deleteUserDto(myUserDtoCreated.getId());

        // THEN
//        UserDto myUpdatedResult =
//                userService.getUserDto(myUserDtoCreated.getId());

        System.out.println(UserException.class);
        Assertions.assertThrows(
                UserException.class,
                () -> userService.getUserDto(myUserDtoCreated.getId()));
    }
}
