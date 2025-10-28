package com.rentflex.adminservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminUserResponse {
    private Long id;

    private String userName;

    private String email;

    @JsonIgnore private String password;

    private Role role;

    private Status status;

    @JsonIgnore private String message;
}
