package org.kamal.library_management.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class EmailNotificationService {

    @Async
    public void sendOrderConfirmationEmail(String memberEmail, Long orderId) {
        log.info("[{}] Starting to send confirmation email for order {} to {}",
                LocalDateTime.now(), orderId, memberEmail);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Email sending interrupted for order {}", orderId);
            return;
        }

        log.info("[{}] Finished sending confirmation email for order {} to {}",
                LocalDateTime.now(), orderId, memberEmail);
    }
}