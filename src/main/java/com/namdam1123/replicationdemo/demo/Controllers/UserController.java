package com.namdam1123.replicationdemo.demo.Controllers;

import com.namdam1123.replicationdemo.demo.Models.RespondObject;
import com.namdam1123.replicationdemo.demo.Models.User;
import com.namdam1123.replicationdemo.demo.Repositories.UserRepository;
import org.apache.coyote.Response;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    // DI
    @Autowired
    private UserRepository repository;

    private CommandGateway commandGateway;


    @GetMapping
    List<User> Users(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    ResponseEntity<RespondObject> findbyId(@PathVariable String id){
        Optional<User> user = repository.findById(id);
        return(user.isPresent()?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Querry user successfully", user)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not Found", "Cannot find product with id = " + id, "")
                ));
    }
    @PostMapping
    ResponseEntity<RespondObject> insertUser(@RequestBody User newUser){
        String userId = UUID.randomUUID().toString();
        newUser.setUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Ok", "Add user successfully", repository.save(newUser))
        );
    }
}
