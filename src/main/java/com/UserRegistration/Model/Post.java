package com.UserRegistration.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Please enter the title.")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message = "Please write something.")
    @Column(name = "content",nullable = false,columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at",nullable = false)
    private Date createAt;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Collection<Comment> comments;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    private User user;

    public Post() {
    }

    public Post(long id, String title, String content, Date createAt, Collection<Comment> comments, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.comments = comments;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
