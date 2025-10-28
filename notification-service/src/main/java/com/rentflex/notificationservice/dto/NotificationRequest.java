package com.rentflex.notificationservice.dto;

public record NotificationRequest(String recipient, String type, String message) {}
