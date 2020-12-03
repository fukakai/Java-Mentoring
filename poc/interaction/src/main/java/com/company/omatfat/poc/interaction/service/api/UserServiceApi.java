package com.company.omatfat.poc.interaction.service.api;

import com.company.omatfat.poc.interaction.dto.UserDto;
import com.company.omatfat.poc.interaction.exception.MetierApiException;
import com.company.omatfat.poc.interaction.exception.UserException;

import java.util.List;

/**
 * @author Romain DALICHAMP - romain.dalichamp@alithya.com
 * <p>
 * Contient toutes les méthodes publiques de son implémentation
 */
public interface UserServiceApi {
    UserDto getUserDto(Long id) throws MetierApiException, UserException;
    List<UserDto> getTwoUserDto(Long id, Long id2) throws MetierApiException, UserException;
    List<UserDto> getAllUserDto() throws MetierApiException, UserException ;
    UserDto addUserDto(UserDto userDto) throws MetierApiException, UserException ;
    UserDto updateUserDto(UserDto userDto) throws MetierApiException, UserException ;
    void deleteUserDto(Long id) throws MetierApiException, UserException;
}
