package com.namdam1123.replicationdemo.demo.Querry;

import com.namdam1123.replicationdemo.demo.Models.User;
import com.namdam1123.replicationdemo.demo.Repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class UserQueryHandler {
    // Inject các dịch vụ cần thiết để lấy dữ liệu từ database
    private final UserRepository userRepository;

    public UserQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    public List<User> handle(GetAllUserQuerry query) {
        return userRepository.findAll();
    }
    @QueryHandler
    public User handle(GetUserByIdQuerry querry){
        Optional<User> user = userRepository.findById(querry.userId);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }
}
