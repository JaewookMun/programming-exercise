package com.github.jaewookmun.springevent.transaction;

import com.github.jaewookmun.springevent.simple.CustomSpringEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TransactionBoundEventListener {

    /**
     * Binding is possible to the following transaction phases:
     * AFTER_COMMIT (default) is used to fire the event if the transaction has completed successfully.
     * AFTER_ROLLBACK – if the transaction has rolled back
     * AFTER_COMPLETION – if the transaction has completed (an alias for AFTER_COMMIT and AFTER_ROLLBACK)
     * BEFORE_COMMIT is used to fire the event right before transaction commit.
     */
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleCustom(CustomSpringEvent event) {
        System.out.println("Handling event inside a transaction BEFORE COMMIT.");
    }
}
