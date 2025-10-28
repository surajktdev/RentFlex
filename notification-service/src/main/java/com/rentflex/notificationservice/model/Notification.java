package com.rentflex.notificationservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "notifications")
@Data
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;
    private String type;
    private String message;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    private boolean delivered;

    public Notification(String recipient, String type, String message) {
        this.recipient = recipient;
        this.type = type;
        this.message = message;
        this.sentAt = LocalDateTime.now();
        this.delivered = false;
    }
}
