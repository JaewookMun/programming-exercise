package com.github.jaewookmun.springevent.transaction;

import com.github.jaewookmun.springevent.domain.User;
import com.github.jaewookmun.springevent.domain.UserEvent;
import com.github.jaewookmun.springevent.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserEventListenerTest {

    @Autowired
    ApplicationEventPublisher publisher;
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Commit
    void test() {
        User james = new User("james");
        userRepository.save(james);

        UserEvent event = new UserEvent(james.getName(), UserEvent.EventType.CREATE);
        System.out.println("publish event");
        publisher.publishEvent(event);
    }


}