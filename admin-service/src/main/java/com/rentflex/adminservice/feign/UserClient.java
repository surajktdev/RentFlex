package com.rentflex.adminservice.feign;

import com.rentflex.adminservice.dto.AdminUserResponse;
import com.rentflex.adminservice.dto.ManageUserRequest;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "${services.user-service.url}")
public interface UserClient {

    @GetMapping("/api/users")
    List<AdminUserResponse> getAllUsers();

    @PostMapping("/api/users")
    AdminUserResponse createUser(@RequestBody ManageUserRequest request);

    @PutMapping("/api/users/{id}")
    AdminUserResponse updateUser(@PathVariable Long id, @RequestBody ManageUserRequest request);

    @DeleteMapping("/api/users/{id}")
    void deleteUser(@PathVariable Long id);
}
