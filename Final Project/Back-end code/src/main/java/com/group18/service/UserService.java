package com.group18.service;

import com.group18.entity.User;
import com.group18.entity.VerificationToken;
import com.group18.entity.dto.request.LoginRequest;
import com.group18.entity.dto.request.ProfileUpdateRequest;
import com.group18.entity.dto.request.SignupRequest;
import com.group18.entity.dto.response.JwtResponse;
import com.group18.entity.dto.response.MessageResponse;
import com.group18.entity.dto.response.ProfileResponse;
import com.group18.exception.NotFoundException;
import org.springframework.http.ResponseEntity;

public interface UserService {

	User getUserById(Long id);

	ProfileResponse getProfileById(Long id);

	User getUserByUsername(String username);

	ResponseEntity<MessageResponse> registerUser(SignupRequest signupRequest) throws NotFoundException;

	ResponseEntity<JwtResponse> authenticate(LoginRequest loginRequest);

	String updateUserProfile(ProfileUpdateRequest profileUpdateRequest);

	VerificationToken getVerificationToken(String token);

	User updateUser(User user);

}
