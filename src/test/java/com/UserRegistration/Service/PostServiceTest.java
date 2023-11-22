package com.UserRegistration.Service;

import com.UserRegistration.Model.Post;
import com.UserRegistration.Repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private PostRepository repository;
    @InjectMocks
    private PostService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllPosts() {
        List<Post> mockPosts = Arrays.asList(new Post(),new Post());
        when(repository.findAll()).thenReturn(mockPosts);

        Collection<Post> result = service.getAllPost();
        assertIterableEquals(mockPosts, result);
    }

    @Test
    void shouldReturnPostByID() {
        long id = 1;
        Post mockPost = new Post();
        when(repository.findById(id)).thenReturn(Optional.of(mockPost));

        Optional<Post> result = service.getByID(id);
        assertEquals(Optional.of(mockPost),result);
    }

    @Test
    void shouldSavePost() {
        Post mockPost = new Post();
        when(repository.save(mockPost)).thenReturn(mockPost);

        Post result = service.save(mockPost);
        assertEquals(mockPost,result);
        verify(repository, times(1)).save(mockPost);
    }

    @Test
    void shouldDeletePost() {
        Post mockPost = new Post();
        service.delete(mockPost);
        verify(repository,times(1)).delete(mockPost);
    }
}