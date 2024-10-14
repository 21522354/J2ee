package com.namdam1123.replicationdemo.demo.Command;

import com.namdam1123.replicationdemo.demo.Events.UserCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCommandHandler {
    private final EventGateway eventGateway;

    @Autowired
    public UserCommandHandler(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }
    @CommandHandler
    public void handle(CreateUserCommand command) {
        // Xử lý logic để tạo user, có thể thêm các kiểm tra khác ở đây
        // Phát ra event sau khi command đã xử lý thành công
        UserCreatedEvent event = new UserCreatedEvent(command.getUserId(), command.getUserName(), command.getPassword(),command.getEmail());
        eventGateway.publish(event);
    }
}
