package com.example.hanghaepluspretask.api.users.dto.in;

import com.example.hanghaepluspretask.api.users.UserType;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserLoginRequest {

	private String nickname;

	@NotNull(message = "유저 타입은 필수 항목입니다.")
	private UserType userType;

	private String rawPassword;

}
