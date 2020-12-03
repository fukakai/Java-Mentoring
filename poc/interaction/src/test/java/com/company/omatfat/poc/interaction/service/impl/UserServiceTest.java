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
class UserServiceTest {
    /*
        /!\ ----- Test name = ClassNameTest                                /!\
        /!\ ----- SERVICES are the only revelent classes to be Unit Test   /!\
        /!\ ----- PUBLIC Methods are to test ONLY                          /!\
     */

    @Autowired
    UserServiceApi userService;

    private UserDto DtoCreation(String firstName, String lastName, Integer old) {
        UserDto dtoCree = new UserDto();
        dtoCree.setFirstName(firstName);
        dtoCree.setLastName(lastName);
        dtoCree.setOld(old);
        return dtoCree;
    }

    @Test
    @DisplayName("GIVEN user DTO without ID WHEN i get by id THEN I expect an exception")
    void addAndGetUserDtoExceptionTest() throws UserException {
        //Given
        UserDto userExpLudwig = DtoCreation("Ludwig", "Picot", 30);
        userExpLudwig.setId(0L);

        //Then
        Assertions.assertThrows(
                UserException.class,
                () -> userService.getUserDto(userExpLudwig.getId()));
    }

    @Test
        @DisplayName("GIVEN 2 user DTO without ID WHEN i get by id THEN I expect to get a list of 2 user DTO")
        void getTwoUserTest() throws UserException {
            //Given
            UserDto userExpLudwig1 = DtoCreation("Ludwig", "Picot", 30);
            UserDto userExpLudwig2 = DtoCreation("Ludwigo", "Picote", 31);

            //when
            UserDto userExpLudwig1Added = userService.addUserDto(userExpLudwig1);
            UserDto userExpLudwig2Added = userService.addUserDto(userExpLudwig2);
            List<UserDto> listUserDto = userService.getTwoUserDto(
                    userExpLudwig1Added.getId(),
                    userExpLudwig2Added.getId());

            //Then
            Assertions.assertTrue(
                    listUserDto.contains(userExpLudwig1Added ) &&
                listUserDto.contains(userExpLudwig2Added));
        Assertions.assertEquals(2, listUserDto.size());

        //Finally
        userService.deleteUserDto(userExpLudwig1Added.getId());
        userService.deleteUserDto(userExpLudwig2Added.getId());
    }

    @Test
    @DisplayName("GIVEN fake UserDto WHEN i get by id THEN i expect the correct infos")
    void addAndGetUserDtoTest() throws Exception {
//        // GIVEN - Fake Data
//        UserDto userEntity = new UserDto();
//        userEntity.setFirstName("Anthony");
//        userEntity.setLastName("Stark");
//        userEntity.setOld(50);
//        UserDto myUserDtoCreated = userService.addUserDto(userEntity);
//
//        // WHEN - method to test
//        UserDto myResult = userService.getUserDto(
//                myUserDtoCreated.getId()
//        );
//
//        // THEN - Expected Result
//        Assertions.assertTrue(myResult.getFirstName().equals("Anthony"));
//        Assertions.assertTrue(myResult.getLastName().equals("Stark"));
//        Assertions.assertTrue(myResult.getOld().equals(50));
//
//        // FINALLY - clean
//        userService.deleteUserDto(myUserDtoCreated.getId());

        //Given
        UserDto userOneLudwig = DtoCreation("Ludwig", "Picot", 30);

        //When
        UserDto userCreatedLudOne = userService.addUserDto(userOneLudwig);

        //Then
        Assertions.assertTrue(userService.getUserDto(userCreatedLudOne.getId()).getFirstName().equals
                (userOneLudwig.getFirstName()));

        //Finally
        userService.deleteUserDto(userCreatedLudOne.getId());
    }

    @Test
    @DisplayName("GIVEN fake List of UserDto WHEN i get all THEN i expect a list containing theses users")
    void getUserDtoListTest() {
//        // GIVEN - Fake Data
//        UserDto userEntityOne = new UserDto();
//        userEntityOne.setFirstName("Anthony");
//        userEntityOne.setLastName("Stark");
//        userEntityOne.setOld(50);
//
//        UserDto userEntityTwo = new UserDto();
//        userEntityTwo.setFirstName("Anthony");
//        userEntityTwo.setLastName("Stark");
//        userEntityTwo.setOld(50);
//
//        List<UserDto> myUserDtoList = new ArrayList<>();
//        myUserDtoList.add(userEntityOne);
//        myUserDtoList.add(userEntityTwo);
//        UserDto myUserDtoCreatedOne = userService.addUserDto(userEntityOne);
//        UserDto myUserDtoCreatedTwo = userService.addUserDto(userEntityTwo);
//
//        // WHEN - method to test
//        List<UserDto> myResult = userService.getAllUserDto();
//
//        // THEN - Expected Result
//        Assertions.assertTrue(myResult.contains(myUserDtoCreatedOne));
//        Assertions.assertTrue(myResult.contains(myUserDtoCreatedTwo));
//
//        // FINALLY - clean database
//        userService.deleteUserDto(myUserDtoCreatedOne.getId());
//        userService.deleteUserDto(myUserDtoCreatedTwo.getId());

        //Given
        UserDto userOneLudwig = DtoCreation("Ludwig", "Picot", 30);
        UserDto userTwoLudwig = DtoCreation("Ludwigo", "Picote", 31);

        //When
        UserDto userCreatedLudOne = userService.addUserDto(userOneLudwig);
        UserDto userCreatedLudTwo = userService.addUserDto(userTwoLudwig);

        //Then
        Assertions.assertTrue(
                userService.getAllUserDto().contains(userCreatedLudOne) &&
                        userService.getAllUserDto().contains(userCreatedLudTwo));

        //Finally
        userService.deleteUserDto(userCreatedLudOne.getId());
        userService.deleteUserDto(userCreatedLudTwo.getId());
    }

    @Test
    @DisplayName("GIVEN a UserDto WHEN i get update it THEN the updated User is saved in database")
    void updateUserDtoTest() {
//        // GIVEN - Fake Data
//        UserDto userDto = new UserDto();
//        userDto.setFirstName("Anthony");
//        userDto.setLastName("Stark");
//        userDto.setOld(50);
//
//        // WHEN- create
//        UserDto myUserDtoCreated = userService.addUserDto(userDto);
//
//        // THEN
//        Assertions.assertTrue(myUserDtoCreated.getFirstName().equals("Anthony"));
//
//        // WHEN - update
//        myUserDtoCreated.setFirstName("Tony");
//        UserDto myUserDtoUpdated = userService.updateUserDto(myUserDtoCreated);
//        Assertions.assertNotNull(myUserDtoUpdated);
//
//        // THEN
//        UserDto myUpdatedResult =
//                userService.getUserDto(myUserDtoCreated.getId());
//        Assertions.assertTrue(myUpdatedResult.getFirstName().equals("Tony"));
//
//        // FINALLY - clean database
//        userService.deleteUserDto(myUserDtoCreated.getId());

        //Given
        UserDto userOneLudwig = DtoCreation("Ludwig", "Picot", 30);

        //When
        UserDto userCreatedLudOne = userService.addUserDto(userOneLudwig);

        //Then check du add
        Assertions.assertTrue(userOneLudwig.getFirstName().equals
                (userService.getUserDto(userCreatedLudOne.getId()).getFirstName()));

        //When
        userCreatedLudOne.setFirstName("TestFirstName");
        userOneLudwig.setFirstName("TestFirstName");
        userService.updateUserDto(userCreatedLudOne);

        //Then check du update
        Assertions.assertTrue(userOneLudwig.getFirstName().equals
                (userService.getUserDto(userCreatedLudOne.getId()).getFirstName()));

        //Finally
        userService.deleteUserDto(userCreatedLudOne.getId());
    }

    @Test
    @DisplayName("GIVEN a UserDto WHEN i save and delete it THEN it is not present in the database")
    void deleteUserDtoTest() throws Exception {
//        // GIVEN - Fake Data
//        UserDto userEntity = new UserDto();
//        userEntity.setFirstName("Anthony");
//        userEntity.setLastName("Stark");
//        userEntity.setOld(50);
//
//        // WHEN- create
//        UserDto myUserDtoCreated = userService.addUserDto(userEntity);
//
//        // THEN
//        Assertions.assertTrue(myUserDtoCreated.getFirstName().equals("Anthony"));
//
//        // WHEN - delete
//        userService.deleteUserDto(myUserDtoCreated.getId());
//
//        // THEN
////        UserDto myUpdatedResult =
////                userService.getUserDto(myUserDtoCreated.getId());
//
//        System.out.println(UserException.class);
//        Assertions.assertThrows(
//                UserException.class,
//                () -> userService.getUserDto(myUserDtoCreated.getId()));

        //Given
        UserDto userOneLudwig = DtoCreation("Ludwig", "Picot", 30);

        //When
        UserDto userCreatedLudOne = userService.addUserDto(userOneLudwig);

        //Then check qu'il existe
        Assertions.assertTrue(userOneLudwig.getFirstName().equals
                (userService.getUserDto(userCreatedLudOne.getId()).getFirstName()));

        //When
        userService.deleteUserDto(userCreatedLudOne.getId());

        //Then check du delete
        Assertions.assertThrows(
                UserException.class,
                () -> userService.getUserDto(userCreatedLudOne.getId()));
    }
}
