package com.fabbi.news.api;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabbi.news.dto.JwtDTO;
import com.fabbi.news.dto.SigninDTO;
import com.fabbi.news.dto.MessageDTO;
import com.fabbi.news.dto.SignupDTO;
import com.fabbi.news.entity.RoleEntity;
import com.fabbi.news.entity.UserEntity;
import com.fabbi.news.repository.RoleRepository;
import com.fabbi.news.repository.UserRepository;
import com.fabbi.news.service.impl.UserDetailsImpl;
import com.fabbi.news.util.ERole;
import com.fabbi.news.util.JwtUtils;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthAPI {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody SigninDTO loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername()
						, loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(r -> r.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtDTO(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupDTO signupRequest) {
		if (userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageDTO("Error: Username is already taken!"));
		}
		
		UserEntity user = new UserEntity(
				signupRequest.getUsername()
				, passwordEncoder.encode(signupRequest.getPassword()));
		Set<String> strRoles = signupRequest.getRole();
		Set<RoleEntity> roles = new HashSet<>();
		
		if (strRoles == null) {
			RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch(role) {
				case "admin":
					RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "mod":
					RoleEntity modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
					break;
				default:
					RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		
		user.setRoles(roles);
		userRepository.save(user);
		
		return ResponseEntity.ok(new MessageDTO("User registered successfully!"));
	}
}
