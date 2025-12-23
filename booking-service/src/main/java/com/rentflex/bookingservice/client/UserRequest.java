package com.rentflex.bookingservice.client;

public record UserRequest(String userName, String email, String password, Role role) {}
