package com.UserRegistration.Service;

import com.UserRegistration.Model.Comment;
import com.UserRegistration.Repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @Mock
    private CommentRepository repository;
    @InjectMocks
    private CommentService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveComment() {
        Comment mockComment = new Comment();
        when(repository.save(mockComment)).thenReturn(mockComment);

        Comment result = service.save(mockComment);

        assertEquals(mockComment,result);
        verify(repository, times(1)).save(mockComment);
    }

    @Test
    void shouldDeleteComment() {
        Comment mockComment = new Comment();
        service.delete(mockComment);
        verify(repository,times(1)).delete(mockComment);
    }
}