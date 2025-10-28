package com.rentflex.vendorservice.client;

public record UserRequest(String userName, String email, String password, Role role) {}
