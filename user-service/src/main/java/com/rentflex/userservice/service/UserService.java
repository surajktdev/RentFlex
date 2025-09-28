package com.rentflex.userservice.service;

import com.rentflex.userservice.dto.Request;
import com.rentflex.userservice.dto.Response;
import java.util.List;

public interface UserService {

    Response registerUser(Request request);

    Response getUserProfileById(Long userId);

    List<Response> getAllUserProfile();

    Response updateUserProfile(Long userId);

    void deleteUser(Long userId);

    void updateUserRole(Long userId);

    void updateUserStatus(Long userId);
}
