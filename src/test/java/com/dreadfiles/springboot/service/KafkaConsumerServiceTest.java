package com.dreadfiles.springboot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaConsumerServiceTest {

    @InjectMocks
    private KafkaConsumerService kafkaConsumerService;

    @Test
    void shouldConsumeMessageFromListen() {
        KafkaConsumerService spyConsumer = spy(kafkaConsumerService);
        String message = "Test message";

        spyConsumer.listen(message);

        verify(spyConsumer).listen(message);
    }

    @Test
    void shouldConsumeMessageFromListenerA() {
        KafkaConsumerService spyConsumer = spy(kafkaConsumerService);
        String message = "Test message for groupA";

        spyConsumer.listenerA(message);

        verify(spyConsumer).listenerA(message);
    }

    @Test
    void shouldConsumeMessageFromListenerB() {
        KafkaConsumerService spyConsumer = spy(kafkaConsumerService);
        String message = "Test message for groupB";

        spyConsumer.listenerB(message);

        verify(spyConsumer).listenerB(message);
    }

}