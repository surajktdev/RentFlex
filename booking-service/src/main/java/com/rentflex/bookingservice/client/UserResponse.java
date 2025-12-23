package com.rentflex.bookingservice.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;

    private String userName;

    private String email;

    @JsonIgnore private String password;

    private Role role;

    private Status status;

    @JsonIgnore private String message;
}
