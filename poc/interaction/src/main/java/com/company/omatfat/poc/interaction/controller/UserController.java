package com.company.omatfat.poc.interaction.controller;

import com.company.omatfat.poc.interaction.dto.UserDto;
import com.company.omatfat.poc.interaction.exception.MetierApiException;
import com.company.omatfat.poc.interaction.exception.UserException;
import com.company.omatfat.poc.interaction.service.api.UserServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

/**
 * @author Romain DALICHAMP - romain.dalichamp@alithya.com
 * <p>
 */
//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    // @Autowired est remplacé par ==> @RequiredArgsConstructor de Lombok
    // Important: "final" pour que Spring fasse l'injection de dépendance dans le constructeur
    // permet d'avoir les exceptions dès l'initialisation de la classe
    private final UserServiceApi userServiceInterface;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}") // GET http://----/users/1
    public UserDto getUserDto(@PathVariable Long id) throws MetierApiException, UserException {
        return userServiceInterface.getUserDto(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping // GET http://----/users/
    public Iterable<UserDto> getAllUserDto() throws MetierApiException, UserException {
        return userServiceInterface.getAllUserDto();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}/{id2}") // GET http://----/users/1
    public Iterable<UserDto> getTwoUserDto(@PathVariable Long id, @PathVariable Long id2) throws MetierApiException, UserException {
        return userServiceInterface.getTwoUserDto(id, id2);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping // POST http://----/users/
    public UserDto addUserDto(
            @RequestBody UserDto userDto) throws MetierApiException, UserException {
        return userServiceInterface.addUserDto(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping // PUT http://----/users/
    public UserDto updateUserDto(
            @RequestBody UserDto userDto) throws MetierApiException, UserException {
        if(userDto.getId()!=null)
            return userServiceInterface.updateUserDto(userDto);
        else
            return null; // exception
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}") // DELETE http://---/users/1
    public void deleteUserDto(@PathVariable Long id) throws MetierApiException, UserException {
        userServiceInterface.deleteUserDto(id);
    }
}
