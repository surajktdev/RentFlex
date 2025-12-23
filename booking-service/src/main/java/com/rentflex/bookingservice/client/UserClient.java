package com.rentflex.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8086/")
public interface UserClient {

    @GetMapping("/api/v1/users/{id}")
    UserResponse getUserById(@PathVariable("id") Long id);
}
