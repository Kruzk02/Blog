package com.UserRegistration;

import com.UserRegistration.Repository.UserRepository;
import com.UserRegistration.User.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RepositoryTest {

    @Autowired private UserRepository repository;
    @Autowired private TestEntityManager entityManager;

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setFirstName("hello");
        user.setLastName("world");
        user.setEmail("hlwld@gmail.com");
        user.setPassword("2023");


        User savedUser = repository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }

    @Test
    public void testFindUserByEmail(){
        String email = "hlw@gmail.com";
        User user = repository.findUserByEmail(email);

        assertThat(user.getEmail()).isEqualTo(email);
    }
}
