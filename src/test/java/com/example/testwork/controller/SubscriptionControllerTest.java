package com.example.testwork.controller;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.dto.response.SubscriptionTopResponse;
import com.example.testwork.dto.response.SubscriptionUserDataResponse;
import com.example.testwork.service.SubscriptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SubscriptionControllerTest {

    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private SubscriptionController subscriptionController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createSubscription_ShouldReturnCreated() throws Exception {
        Long userId = 1L;
        SubscriptionDataCreateRequest request = new SubscriptionDataCreateRequest();
        SubscriptionDataCreateResponse response = new SubscriptionDataCreateResponse();

        when(subscriptionService.addSubscriptionByUserById(eq(userId), any(SubscriptionDataCreateRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/subscriptions/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(subscriptionService, times(1)).addSubscriptionByUserById(eq(userId), any());
    }

    @Test
    void getSubscriptionByUserId_ShouldReturnSubscriptions() throws Exception {
        Long userId = 1L;
        List<SubscriptionUserDataResponse> responses = List.of(new SubscriptionUserDataResponse());

        when(subscriptionService.getSubscriptionByUserId(userId)).thenReturn(responses);

        mockMvc.perform(get("/api/v1/subscriptions/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(subscriptionService, times(1)).getSubscriptionByUserId(userId);
    }

    @Test
    void deleteSubscription_ShouldReturnNoContent() throws Exception {
        Long userId = 1L;
        Long subId = 2L;

        doNothing().when(subscriptionService).deleteSubscriptionByIdsUserSubscription(userId, subId);

        mockMvc.perform(delete("/api/v1/subscriptions/{sub_id}/users/{id}", subId, userId))
                .andExpect(status().isNoContent());

        verify(subscriptionService, times(1)).deleteSubscriptionByIdsUserSubscription(userId, subId);
    }

    @Test
    void getTop3PopularSubscriptions_ShouldReturnTopSubscriptions() throws Exception {
        List<SubscriptionTopResponse> topSubscriptions = List.of(new SubscriptionTopResponse());

        when(subscriptionService.getTop3PopularSubscriptions()).thenReturn(topSubscriptions);

        mockMvc.perform(get("/api/v1/subscriptions/top")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(subscriptionService, times(1)).getTop3PopularSubscriptions();
    }
}
