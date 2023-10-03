package com.hello.World.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "description")
    private String description;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany
    @JoinColumn(name = "postId")
    private List<Comment> comments;

    public Post() {
    }

    public Post(long id, String description, Date createdAt) {
        this.id = id;
        this.description = description;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
