package org.garvk.user_service.controller;

import jakarta.ws.rs.Path;
import org.apache.coyote.Response;
import org.garvk.user_service.model.User;
import org.garvk.user_service.model.UserCredDTO;
import org.garvk.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService aInUserService){
        this.userService = aInUserService;
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User aInUser){
        return new ResponseEntity<>(userService.createUser(aInUser), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long aInId){
        return new ResponseEntity<>(userService.getUserById(aInId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User aInUser){
        return new ResponseEntity<>(userService.updateUser(aInUser), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long aInId){
        userService.deleteUser(aInId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("validate")
    public ResponseEntity<Boolean> validateUser(@RequestBody UserCredDTO aInUserCred){
        return new ResponseEntity<>(userService.validateUser(aInUserCred), HttpStatus.OK);
    }

    @GetMapping("{id}/isConsumer")
    public ResponseEntity<Boolean> isUserConsumer(@PathVariable("id") Long aInId){
        return new ResponseEntity<>(userService.isUserConsumer(aInId), HttpStatus.OK);
    }

    @GetMapping("{id}/isOwner")
    public ResponseEntity<Boolean> isUserOwner(@PathVariable("id") Long aInId){
        return new ResponseEntity<>(userService.isUserOwner(aInId), HttpStatus.OK);
    }

}
