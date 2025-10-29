package com.dreadfiles.springboot.controller;

import com.dreadfiles.springboot.service.KafkaProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaControllerTest {

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private KafkaController kafkaController;

    private String message;

    @BeforeEach
    void setup() {
        message = "Test message";
    }

    @Test
    void shouldSendMessageToKafka() {
        String response = kafkaController.sendMessage(message);

        assertThat(response).isEqualTo("Message sent to Kafka topic: " + message);
        verify(kafkaProducerService).sendMessage(message);
    }

}
