package com.rentflex.notificationservice.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCreateEvent {
    private Long bookingId;
    private Long userId;
    private Long itemId;
    private BookingStatus status;

}
