package com.github.jaewookmun.springevent.transaction;

import com.github.jaewookmun.springevent.domain.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class UserEventListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleCustom(UserEvent event) throws InterruptedException{
        System.out.println("Handling event outside a transaction AFTER_COMMIT. " + event.getName());
    }
}
