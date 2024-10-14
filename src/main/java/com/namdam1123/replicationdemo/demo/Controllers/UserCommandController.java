package com.namdam1123.replicationdemo.demo.Controllers;

import com.namdam1123.replicationdemo.demo.Command.CreateUserCommand;
import com.namdam1123.replicationdemo.demo.Models.RespondObject;
import com.namdam1123.replicationdemo.demo.Models.User;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users/commands")
public class UserCommandController {
    private final CommandGateway commandGateway;

    public UserCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    ResponseEntity<RespondObject> insertUser(@RequestBody User newUser){
        // Tạo ID mới cho user
        String userId = UUID.randomUUID().toString();
        newUser.setUserId(userId);

        // Tạo Command để phát đi
        CreateUserCommand command = new CreateUserCommand(userId, newUser.getUserName(), newUser.getPassword(), newUser.getEmail());

        // Gửi Command tới hệ thống qua CommandGateway
        commandGateway.send(command);

        // Trả về kết quả ngay lập tức (Command xử lý bất đồng bộ)
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("OK", "User created successfully, waiting for event to process", newUser)
        );
    }
}
