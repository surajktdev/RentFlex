package com.rentflex.userservice.controller;

import com.rentflex.userservice.dto.Request;
import com.rentflex.userservice.dto.Response;
import com.rentflex.userservice.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Operations", description = "Endpoints for handling user-related functionalities")
public class UserController {

    @Autowired private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<Response> register(@RequestBody Request request) {
        Response response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }
}
