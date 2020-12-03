package com.company.omatfat.poc.interaction.service.impl;

import com.company.omatfat.poc.interaction.dto.UserDto;
import com.company.omatfat.poc.interaction.exception.MetierApiException;
import com.company.omatfat.poc.interaction.exception.UserException;
import com.company.omatfat.poc.interaction.restclient.UserRestClient;
import com.company.omatfat.poc.interaction.service.api.UserServiceApi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Romain DALICHAMP - romain.dalichamp@alithya.com
 * <p>
 * REST Template documentation: https://howtodoinjava.com/spring-boot2/resttemplate/spring-restful-client-resttemplate-example/
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserServiceApi {

    private final UserRestClient userRestClient;

    public UserDto getUserDto(Long id) throws MetierApiException, UserException {
        // Business rules
        return userRestClient.getUserDto(id);
    }

    public List<UserDto> getTwoUserDto(Long id, Long id2) throws MetierApiException, UserException {
        return userRestClient.getTwoUserDto(id, id2);
    }

    public List<UserDto> getAllUserDto() throws MetierApiException, UserException {
        // Business rules
        return userRestClient.getAllUserDto();
    }

    public UserDto addUserDto(UserDto userDto) throws MetierApiException, UserException {
        // Business rules
        return userRestClient.addUserDto(userDto);
    }

    public UserDto updateUserDto(UserDto userDto) throws MetierApiException, UserException {
        // Business rules
        return userRestClient.updateUserDto(userDto);
    }

    public void deleteUserDto(Long id) throws MetierApiException, UserException {
        // Business rules
        userRestClient.deleteUserDto(id);
    }
}
