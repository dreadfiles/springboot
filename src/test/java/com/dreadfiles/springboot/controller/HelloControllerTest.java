package com.dreadfiles.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    void shouldReturnHelloToName() throws Exception {
        mockMvc.perform(get("/hello/Adrian"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Adrian!"));
    }

    @Test
    void shouldReturnGreetingWithRequestParam() throws Exception {
        mockMvc.perform(get("/greet").param("name", "Adrian"))
                .andExpect(status().isOk())
                .andExpect(content().string("Greetings, Adrian!"));
    }

    @Test
    void shouldReturnGreetingWithDefaultValue() throws Exception {
        mockMvc.perform(get("/greet"))
                .andExpect(status().isOk())
                .andExpect(content().string("Greetings, Guest!"));
    }

    @Test
    void shouldCreatePersonFromRequestBody() throws Exception {
        String jsonBody = """
                {
                    "name": "Adrian",
                    "age": 30
                }
                """;

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Created person: Adrian, Age: 30"));
    }

}