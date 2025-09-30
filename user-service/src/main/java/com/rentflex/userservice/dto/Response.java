package com.rentflex.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentflex.userservice.model.Role;
import com.rentflex.userservice.model.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private Long id;

    private String userName;

    private String email;

    @JsonIgnore private String password;

    private Role role;

    private Status status;

    private String message;
}
