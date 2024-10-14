package com.namdam1123.replicationdemo.demo.Controllers;

import com.namdam1123.replicationdemo.demo.Models.Post;
import com.namdam1123.replicationdemo.demo.Models.RespondObject;
import com.namdam1123.replicationdemo.demo.Models.User;
import com.namdam1123.replicationdemo.demo.Repositories.PostRepository;
import com.namdam1123.replicationdemo.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    List<Post> getAllPost(){
        return postRepository.findAll();
    }
//    @GetMapping("/users/{id}")
//    List<Post> getPostByUserId(@PathVariable Long id){
//        return postRepository.findByUser_userId(id);
//    }
    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getById(@PathVariable String id){
        Optional<Post> post = postRepository.findById(id);
        return (post.isPresent()?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Querry post successfully", post)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("NotFound", "Cannot find the post with id = " + id, "")
                ));
    }
    @PostMapping
    ResponseEntity<RespondObject> createPost(@RequestBody Post newPost)
    {
         // Lấy thông tin người dùng từ userId trong newPost
        User user = userRepository.findById(newPost.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        // Gán người dùng vào bài viết
        newPost.setUser(user);

        // Lưu bài viết mới vào cơ sở dữ liệu
        Post savedPost = postRepository.save(newPost);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new RespondObject("OK", "Tạo bài viết thành công", savedPost));
    }
}
