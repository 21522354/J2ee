package com.namdam1123.replicationdemo.demo.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tblPost")
public class Post {

    @Id
    private String postId;

    // Khóa ngoại tham chiếu tới User
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)  // khóa ngoại trong bảng Post, tham chiếu tới trường userId của bảng User
    private User user;

    private String content;
    private Date createdAt;
    private int likeCount;

    public Post() {}

    public Post(String postId, User user, String content, Date createdAt, int likeCount) {
        this.postId = postId;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
