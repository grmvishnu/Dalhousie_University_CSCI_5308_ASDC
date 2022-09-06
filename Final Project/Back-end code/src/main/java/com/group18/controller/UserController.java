package com.group18.controller;

import com.group18.entity.User;
import com.group18.entity.VerificationToken;
import com.group18.entity.dto.request.LoginRequest;
import com.group18.entity.dto.request.ProfileUpdateRequest;
import com.group18.entity.dto.request.SignupRequest;
import com.group18.entity.dto.response.JwtResponse;
import com.group18.entity.dto.response.ProfileResponse;
import com.group18.exception.NotFoundException;
import com.group18.listener.OnRegistrationCompleteEvent;
import com.group18.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.authenticate(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) throws NotFoundException {
        ResponseEntity<?> response = userService.registerUser(signUpRequest);
        User user = userService.getUserByUsername(signUpRequest.getUsername());
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user,
                request.getLocale(), "http://localhost:8080/"));

        return response;
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateUserProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest) {
        String resMessage = userService.updateUserProfile(profileUpdateRequest);
        ResponseEntity<String> response = new ResponseEntity<>(resMessage, HttpStatus.OK);
        return response;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileResponse> getUserProfile(@PathVariable Long id) {
        ProfileResponse profileResponse = userService.getProfileById(id);
        ResponseEntity<ProfileResponse> response = new ResponseEntity<>(profileResponse, HttpStatus.OK);
        return response;
    }

    @GetMapping("/registrationConfirm")
    public ResponseEntity<String> confirmRegistration
            (@RequestParam("token") String token) {
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            ResponseEntity<String> response = new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
            return response;
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            ResponseEntity<String> response = new ResponseEntity<>("Token expired", HttpStatus.BAD_REQUEST);
            return response;
        }

        user.setEnabled(true);
        userService.updateUser(user);
        return new ResponseEntity<>("Email confirmed", HttpStatus.OK);
    }


}