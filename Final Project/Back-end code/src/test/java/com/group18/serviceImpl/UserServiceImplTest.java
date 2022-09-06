package com.group18.serviceImpl;

import com.group18.entity.Role;
import com.group18.entity.User;
import com.group18.entity.VerificationToken;
import com.group18.entity.dto.request.LoginRequest;
import com.group18.entity.dto.request.ProfileUpdateRequest;
import com.group18.entity.dto.request.SignupRequest;
import com.group18.entity.dto.response.JwtResponse;
import com.group18.entity.dto.response.MessageResponse;
import com.group18.entity.dto.response.ProfileResponse;
import com.group18.entity.model.EnumRole;
import com.group18.entity.model.UserDetailsImpl;
import com.group18.exception.NotFoundException;
import com.group18.repository.RoleRepository;
import com.group18.repository.UserRepository;
import com.group18.repository.VerificationTokenRepository;
import com.group18.utils.JwtUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class UserServiceImplTest {
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private VerificationTokenRepository tokenRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    Authentication authentication;

    @BeforeEach
    void setUp(){
        userServiceImpl=new UserServiceImpl(userRepository, encoder, jwtUtil, authenticationManager, tokenRepository, roleRepository,modelMapper);
    }

    @Test
    public void getUserTrue() {
        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername("abcd");

        Optional<User> optinalUser=Optional.of(user);
        Mockito.when(this.userRepository.findById(id)).thenReturn(optinalUser);
        User userById=userServiceImpl.getUserById(id);

        assertTrue(userById.getUsername().equals("abcd"));
    }

    @Test
    public void getUserNotNull() {
        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername("abcd");

        Optional<User> optinalUser=Optional.of(user);
        Mockito.when(this.userRepository.findById(id)).thenReturn(optinalUser);
        User userById=userServiceImpl.getUserById(id);

        Assertions.assertThat(userById).isNotNull();
    }

    @Test
    public void getProfileByIdNotNull() {
        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername("abcd");

        ProfileResponse profileResponse=new ProfileResponse();

        Optional<User> optinalUser=Optional.of(user);
        Mockito.when(this.userRepository.findById(id)).thenReturn(optinalUser);
        Mockito.when(modelMapper.map(user, ProfileResponse.class)).thenReturn(profileResponse);
        ProfileResponse profileById=userServiceImpl.getProfileById(id);

        Assertions.assertThat(profileById).isNotNull();
    }

    @Test
    public void getUserByNameTrue() {
        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername("abcd");

        Optional<User> optinalUser=Optional.of(user);
        Mockito.when(this.userRepository.findByUsername("abcd")).thenReturn(optinalUser);
        User userById=userServiceImpl.getUserByUsername("abcd");

        assertTrue(userById.getUsername().equals("abcd"));
    }

    @Test
    public void getUserByNameNotNull() {
        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername("abcd");

        Optional<User> optinalUser=Optional.of(user);
        Mockito.when(this.userRepository.findByUsername("abcd")).thenReturn(optinalUser);
        User userById=userServiceImpl.getUserByUsername("abcd");

        Assertions.assertThat(userById).isNotNull();
    }

    @Test
    public void updateUserProfileNotNull() {
        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername("abcd");

        ProfileUpdateRequest profileUpdateRequest=new ProfileUpdateRequest();
        long profile_id=1;
        profileUpdateRequest.setId(profile_id);

        Optional<User> optinalUser=Optional.of(user);
        Mockito.when(this.userRepository.findById(id)).thenReturn(optinalUser);
        String resp=userServiceImpl.updateUserProfile(profileUpdateRequest);

        Assertions.assertThat(resp).isNotNull();
    }

    @Test
    public void updateUserProfileTrue() {
        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername("abcd");

        ProfileUpdateRequest profileUpdateRequest=new ProfileUpdateRequest();
        long profile_id=1;
        profileUpdateRequest.setId(profile_id);

        Optional<User> optinalUser=Optional.of(user);
        Mockito.when(this.userRepository.findById(id)).thenReturn(optinalUser);
        String resp=userServiceImpl.updateUserProfile(profileUpdateRequest);

        assertTrue(resp.equals("User profile updated"));
    }

    @Test
    public void updateUserNotNull() {
        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername("abcd");


        Optional<User> optinalUser=Optional.of(user);
        Mockito.when(this.userRepository.save(user)).thenReturn(user);
        User updateUser=this.userServiceImpl.updateUser(user);
        Assertions.assertThat(updateUser).isNotNull();
    }

    @Test
    public void getVerificationTokenNotNull() {
        VerificationToken verificationToken=new VerificationToken();


        Mockito.when(tokenRepository.findByToken("token")).thenReturn(verificationToken);
        VerificationToken implVerificationToken=this.userServiceImpl.getVerificationToken("token");
        Assertions.assertThat(implVerificationToken).isNotNull();
    }

    @Test
    public void registerUserExists() throws NotFoundException {
        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername("abcd");

        SignupRequest signupRequest=new SignupRequest();
        signupRequest.setUsername("user");

        Optional<User> optinalUser=Optional.of(user);
        Mockito.when(userRepository.existsByUsername("user")).thenReturn(true);

        ResponseEntity<MessageResponse> messageResponseResponseEntity=this.userServiceImpl.registerUser(signupRequest);

        assertTrue(messageResponseResponseEntity.getBody().getMessage().equals("Error: Username is already taken!"));
    }

    @Test
    public void registerUserExistsFalse() throws NotFoundException {
        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername("abcd");

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_HOST");
        SignupRequest signupRequest=new SignupRequest();
        signupRequest.setUsername("user");
        signupRequest.setPassword("password");
        signupRequest.setRole(roles);

        Role hostRole=new Role();

        Optional<User> optinalUser=Optional.of(user);
        Mockito.when(userRepository.existsByUsername("user")).thenReturn(true);
        Mockito.when(roleRepository.findByName(EnumRole.valueOf("ROLE_HOST"))).thenReturn(Optional.of(hostRole));

        ResponseEntity<MessageResponse> messageResponseResponseEntity=this.userServiceImpl.registerUser(signupRequest);

        assertFalse(messageResponseResponseEntity.getBody().getMessage().equals("User registered successfully!"));
    }

    @Test
    public void authenticateNotNull() {
        User user=new User();
        long id=1;
        user.setId(id);
        user.setUsername("abcd");
        user.setEnabled(true);

        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUsername("login");
        loginRequest.setPassword("pass");

        Collection<? extends GrantedAuthority> authorities=new HashSet<>();
        UserDetailsImpl userDetails=new UserDetailsImpl(id,"login","pass",authorities);

        Optional<User> optinalUser=Optional.of(user);
        Mockito.when(userRepository.findByUsername("login")).thenReturn(optinalUser);
        Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("login","pass"))).thenReturn(authentication);
        Mockito.when(jwtUtil.generateJwtToken(authentication)).thenReturn("abcd");
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);

        ResponseEntity<JwtResponse> jwtResponseResponseEntity=this.userServiceImpl.authenticate(loginRequest);

        Assertions.assertThat(jwtResponseResponseEntity).isNotNull();
    }

}
