package com.example.hanghaepluspretask.api.users;

import com.example.hanghaepluspretask.api.posts.dto.out.PostResponse;
import com.example.hanghaepluspretask.api.users.dto.in.UserLoginRequest;
import com.example.hanghaepluspretask.api.users.dto.in.UserRegisterRequest;
import com.example.hanghaepluspretask.common.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	ResponseEntity<CommonResponse<?>> login(
			@Valid @RequestBody UserLoginRequest userLoginRequest
	) {
		String jwt = userService.login(userLoginRequest);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + jwt);

		CommonResponse<?> response = CommonResponse.ok(null);
		return ResponseEntity.ok()
				.headers(headers)
				.body(response);
	}

	@PostMapping()
	ResponseEntity<CommonResponse<PostResponse>> register(
			@Valid @RequestBody UserRegisterRequest userRegisterRequest
	) {
		userService.register(userRegisterRequest.toEntity(), userRegisterRequest.getUserType());

		CommonResponse<PostResponse> response = CommonResponse.ok(null);
		return ResponseEntity.ok(response);
	}
}
