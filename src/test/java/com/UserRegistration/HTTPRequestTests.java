package com.UserRegistration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HTTPRequestTests {

    @Value(value = "${local.server.port}")
    private int port;
    @Autowired private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnTitleName(){
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Home Page");
    }
}
