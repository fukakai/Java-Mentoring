package com.company.omatfat.poc.metier.controller;

import com.company.omatfat.poc.metier.entity.UserEntity;
import com.company.omatfat.poc.metier.service.api.UserServiceApi;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Romain DALICHAMP - romain.dalichamp@alithya.com
 * <p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    // @Autowired est remplacé par ==> @RequiredArgsConstructor de Lombok
    // Important: "final" pour que Spring fasse l'injection de dépendance dans le constructeur
    // permet d'avoir les exceptions dès l'initialisation de la classe
    private final UserServiceApi userServiceInterface;

    @GetMapping("{id}")
    public Optional<UserEntity> getUserEntity(@PathVariable Long id) {
        return userServiceInterface.getUserEntity(id);
    }

    @GetMapping("{id}/{id2}")
    public List<UserEntity> getTwoUserEntity(@PathVariable Long id, @PathVariable Long id2) {
        return userServiceInterface.getTwoUserEntity(id, id2);
    }

    @GetMapping
    public List<UserEntity> getAllUserEntity() {
        return userServiceInterface.getAllUserEntity();
    }

    @PostMapping
    public UserEntity addUserEntity(@RequestBody UserEntity metierEntity) {
        return userServiceInterface.addUserEntity(metierEntity);
    }

    @PutMapping
    public UserEntity updateUserEntity(@RequestBody UserEntity metierEntity) {
        return userServiceInterface.updateUserEntity(metierEntity);
    }

    @DeleteMapping("{id}")
    public void deleteUserEntity(@PathVariable Long id) {
        userServiceInterface.deleteUserEntity(id);
    }
}
