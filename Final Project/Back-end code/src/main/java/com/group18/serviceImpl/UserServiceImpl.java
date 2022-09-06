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
import com.group18.service.UserService;
import com.group18.utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private VerificationTokenRepository tokenRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager, VerificationTokenRepository tokenRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
		this.tokenRepository = tokenRepository;
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		return user.get();
	}

	@Override
	public ProfileResponse getProfileById(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		if(user.isPresent()) {
			ProfileResponse profileResponse = modelMapper.map(user.get(), ProfileResponse.class);
			return profileResponse;
		} else {
			return null;
		}
	}

	@Override
	public User getUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user.get();
	}

	@Override
	public ResponseEntity<MessageResponse> registerUser(SignupRequest signupRequest) throws NotFoundException {
		if (userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		User user = new User(signupRequest.getUsername(),
				encoder.encode(signupRequest.getPassword()));
		Set<Role> roles = new HashSet<>();
		Set<String> strRoles = signupRequest.getRole();
		if (strRoles == null) {
			Optional<Role> userRole = roleRepository.findByName(EnumRole.ROLE_TRAVELLER);
			if(userRole.isPresent()) {
				roles.add(userRole.get());
			} else {
				throw new NotFoundException("Error: Role is not found.");
			}
		} else {
			strRoles.forEach(role -> {
				Optional<Role> hostRole = roleRepository.findByName(EnumRole.valueOf(role));
				if(hostRole.isPresent()) {
					roles.add(hostRole.get());
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}



	@Override
	public ResponseEntity<JwtResponse> authenticate(LoginRequest loginRequest) {
		Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
		User user = optionalUser.get();
		if (user.isEnabled()) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtil.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			return ResponseEntity.ok(new JwtResponse(jwt,
					userDetails.getId(),
					userDetails.getUsername(),
					roles));
		} else {
			throw new RuntimeException("User is not verfied");
		}
	}

	@Override
	public String updateUserProfile(ProfileUpdateRequest profileUpdateRequest) {
		Optional<User> user = userRepository.findById(profileUpdateRequest.getId());
		if(user.isPresent()) {
			User existingUser = user.get();
			existingUser.setCity(profileUpdateRequest.getCity());
			existingUser.setStreet(profileUpdateRequest.getStreet());
			existingUser.setGender(profileUpdateRequest.getGender());
			existingUser.setFirstName(profileUpdateRequest.getFirstName());
			existingUser.setLastName(profileUpdateRequest.getLastName());
			existingUser.setPostalCode(profileUpdateRequest.getPostalCode());
			existingUser.setMobileNumber(profileUpdateRequest.getMobileNumber());
			userRepository.save(existingUser);
			return "User profile updated";
		} else {
			return "User not found";
		}

	}

	@Override
	public VerificationToken getVerificationToken(String token) {
		return tokenRepository.findByToken(token);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

}
