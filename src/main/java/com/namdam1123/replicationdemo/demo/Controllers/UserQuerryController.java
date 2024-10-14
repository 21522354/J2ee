package com.namdam1123.replicationdemo.demo.Controllers;

import com.namdam1123.replicationdemo.demo.Models.RespondObject;
import com.namdam1123.replicationdemo.demo.Models.User;
import com.namdam1123.replicationdemo.demo.Querry.GetAllUserQuerry;
import com.namdam1123.replicationdemo.demo.Querry.GetUserByIdQuerry;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/v1/users/query")
public class UserQuerryController {
    private QueryGateway queryGateway;

    public UserQuerryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<User> getAllUser(){
        GetAllUserQuerry getAllUserQuerry = new GetAllUserQuerry();

        List<User> listUser = queryGateway.query(getAllUserQuerry, ResponseTypes.multipleInstancesOf(User.class)).join();

        return listUser;
    }
    @GetMapping("/{id}")
    ResponseEntity<RespondObject> findbyId(@PathVariable String id){
        GetUserByIdQuerry getUserByIdQuery = new GetUserByIdQuerry(id);

        // Gửi truy vấn và lấy kết quả
        User user = queryGateway.query(getUserByIdQuery, ResponseTypes.instanceOf(User.class)).join(); // Sử dụng join() để đợi kết quả

        // Kiểm tra nếu user tồn tại và trả về ResponseEntity tương ứng
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Query user successfully", user)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Not Found", "Cannot find user with id = " + id, "")
            );
        }
    }

}
