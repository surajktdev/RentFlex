package com.rentflex.userservice.service;

import com.rentflex.userservice.dto.Request;
import com.rentflex.userservice.dto.Response;
import com.rentflex.userservice.model.Role;
import com.rentflex.userservice.model.Status;
import java.util.List;

public interface UserService {

    Response registerUser(Request request);

    Response getUserProfileById(Long userId);

    List<Response> getAllUserProfile();

    Response updateUserProfile(Long userId, Request request);

    void deleteUser(Long userId);

    Response updateUserRole(Long userId, Role role);

    Response updateUserStatus(Long userId, Status status);
}
