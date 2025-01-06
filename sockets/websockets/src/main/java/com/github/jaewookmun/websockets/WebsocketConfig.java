package com.github.jaewookmun.websockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * [참고]
 * 1. **@EnableWebSocket**
 *     - 기본적인 WebSocket 통신만을 활성화한다.
 *     - WebSocketHandler를 구현하여 저수준의 WebSocket 메시지 처리가 필요할 때 사용
 *     - 단순한 텍스트/바이너리 메시지 교환에 적합
 *
 * 2. **@EnableWebSocketMessageBroker**
 *     - STOMP 프로토콜을 사용한 메시지 브로커 기능을 활성화한다.
 *     - 메시지 라우팅, pub/sub 패턴을 쉽게 구현할 수 있음
 *     - @MessageMapping, @SubscribeMapping 등의 애노테이션 사용 가능
 *     - 메시지 브로커를 통한 더 체계적인 메시지 전달 구조 제공
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
