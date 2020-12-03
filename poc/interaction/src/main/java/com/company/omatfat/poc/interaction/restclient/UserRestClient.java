package com.company.omatfat.poc.interaction.restclient;

import com.company.omatfat.poc.interaction.dto.UserDto;
import com.company.omatfat.poc.interaction.exception.MetierApiException;
import com.company.omatfat.poc.interaction.exception.UserException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Romain DALICHAMP - romain.dalichamp@alithya.com
 * <p>
 * Tony Stark Was Here
 */
@Component
public class UserRestClient extends RestTemplate {

    public static final String jsonUri = "users/";

    @Value("${company.omafat.poc.metier.url}")
    private String metierUrl;

    public UserDto getUserDto(Long id) throws MetierApiException, UserException {
        try {
            UserDto user = getForObject(metierUrl + jsonUri + id, UserDto.class);

            if (Objects.isNull(user)) {
                throw new UserException("User not found");
            }

            return user;
        } catch (MetierApiException e) {
            throw new MetierApiException("Could not contact the Metier API");
        }
    }

    public List<UserDto> getTwoUserDto(Long id, Long id2) throws MetierApiException, UserException {
        try {
            UserDto userDto = getForObject(metierUrl + jsonUri + id, UserDto.class);
            UserDto userDto2 = getForObject(metierUrl + jsonUri + id2, UserDto.class);
            UserDto[] listUserDto = {userDto, userDto2};

            return Arrays.asList(listUserDto);

        } catch (MetierApiException e) {
            throw new MetierApiException("Could not contact the Metier API");
        }
    }

    public List<UserDto> getAllUserDto() throws MetierApiException, UserException {
        try {
            UserDto[] userDtos =
                    getForObject(metierUrl + jsonUri, UserDto[].class);

            if (ArrayUtils.isEmpty(userDtos)) {
                throw new UserException("Could not get all Users list");
            }

            return Arrays.asList(userDtos);
        } catch (MetierApiException e) {
            throw new MetierApiException("Could not contact the Metier API");
        }
    }

    public UserDto addUserDto(UserDto userDto) throws MetierApiException, UserException {
        try {
            UserDto createdUser = postForObject(metierUrl + jsonUri, userDto, UserDto.class);

            if (Objects.isNull(createdUser)) {
                throw new UserException("The User has not been created");
            }

            return createdUser;
        } catch (MetierApiException e) {
            throw new MetierApiException("Could not contact the Metier API");
        }
    }

    public UserDto updateUserDto(UserDto userDto) throws MetierApiException, UserException {
        try {
            put(metierUrl + jsonUri, userDto);
            return getUserDto(userDto.getId());
        } catch (ResourceAccessException e) {
            throw new UserException("Could not update the User: " + userDto.getId());
        } catch (MetierApiException e) {
            throw new MetierApiException("Could not contact the Metier API");
        }
    }

    public void deleteUserDto(Long id) throws MetierApiException, UserException {
        try {
            delete(metierUrl + jsonUri + id);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new UserException("Could not delete the User: " + id);
        } catch (MetierApiException e) {
            throw new MetierApiException("Could not contact the Metier API");
        }
    }
}
