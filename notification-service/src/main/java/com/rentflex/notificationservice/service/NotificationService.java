package com.rentflex.notificationservice.service;

import com.rentflex.notificationservice.dto.NotificationRequest;
import com.rentflex.notificationservice.dto.NotificationResponse;
import com.rentflex.notificationservice.model.Notification;
import com.rentflex.notificationservice.repository.NotificationRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired private NotificationRepository notificationRepository;

    // Send a notification
    public NotificationResponse sendNotification(NotificationRequest request) {
        Notification notification =
                new Notification(request.recipient(), request.type(), request.message());

        // Simulate sending notification
        System.out.println(
                "Sending "
                        + notification.getType()
                        + " to "
                        + notification.getRecipient()
                        + ": "
                        + notification.getMessage());
        notification.setDelivered(true);

        Notification saved = notificationRepository.save(notification);

        return mapToResponse(saved);
    }

    // Get all notifications
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get notification by ID
    public Optional<NotificationResponse> getNotificationById(Long id) {
        return notificationRepository.findById(id).map(this::mapToResponse);
    }

    // Delete a notification
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    private NotificationResponse mapToResponse(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getRecipient(),
                notification.getType(),
                notification.getMessage(),
                notification.getSentAt(),
                notification.isDelivered());
    }
}
