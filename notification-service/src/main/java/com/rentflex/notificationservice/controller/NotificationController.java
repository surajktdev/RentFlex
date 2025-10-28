package com.rentflex.notificationservice.controller;

import com.rentflex.notificationservice.dto.NotificationRequest;
import com.rentflex.notificationservice.dto.NotificationResponse;
import com.rentflex.notificationservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notification Service", description = "APIs for sending and managing notifications")
public class NotificationController {

    @Autowired private NotificationService notificationService;

    @PostMapping("/send")
    @Operation(
            summary = "Send a notification",
            description = "Send Email, SMS, or Push notification to a recipient")
    public ResponseEntity<NotificationResponse> sendNotification(
            @RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.sendNotification(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    @Operation(
            summary = "Get all notifications",
            description = "Retrieve a list of all notifications")
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get notification by ID",
            description = "Retrieve details of a specific notification by ID")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
        return notificationService
                .getNotificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete notification", description = "Delete a notification by ID")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
