package com.yp.ahrenix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yp.ahrenix.dto.request.RegisterRequest;
import com.yp.ahrenix.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @Test
    void shouldRegisterSuccessfully()
            throws Exception {

        RegisterRequest request =
                RegisterRequest.builder()
                        .fullName("Test User")
                        .email("test@ahrenix.com")
                        .password("Password@123")
                        .phone("9876543210")
                        .build();

        mockMvc.perform(
                post("/api/v1/auth/register")
                        .contentType(
                                MediaType.APPLICATION_JSON
                        )
                        .content(
                                objectMapper
                                        .writeValueAsString(request)
                        )
        ).andExpect(status().isOk());
    }

}