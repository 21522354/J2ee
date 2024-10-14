package com.namdam1123.replicationdemo.demo.Events;

import com.namdam1123.replicationdemo.demo.Models.User;
import com.namdam1123.replicationdemo.demo.Repositories.UserRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {
    @Autowired
    private UserRepository userRepository;

    @EventHandler
    public void on(UserCreatedEvent event){
        User user = new User();
        user.setUserId(event.getUserId());
        user.setUserName(event.getUserName());
        user.setPassword(event.getPassword());
        user.setEmail(event.getEmail());
        userRepository.save(user);
    }
}
