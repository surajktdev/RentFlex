package com.rentflex.notificationservice.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class NotificationResponse {

    private Long id;
    private String recipient;
    private String type;
    private String message;
    private LocalDateTime sentAt;
    private boolean delivered;
}
