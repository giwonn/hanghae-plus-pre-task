package com.example.hanghaepluspretask.api.users.dto.in;

import com.example.hanghaepluspretask.api.users.User;
import com.example.hanghaepluspretask.api.users.UserPermission;
import com.example.hanghaepluspretask.api.users.UserType;
import com.example.hanghaepluspretask.util.PasswordEncoderUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserRegisterRequest {

	@Email(message = "잘못된 이메일 형식입니다.")
	private String email;

	@NotBlank(message = "닉네임은 필수 항목입니다.")
	@Size(min = 4, max = 10, message = "닉네임은 최소 2자, 최대 8자입니다.")
	private String nickname;

	@NotNull(message = "유저 타입은 필수 항목입니다.")
	private UserType userType;

	@NotBlank(message = "비밀번호는 필수 항목입니다.")
	@Size(min = 8, max = 15, message = "비밀번호는 최소 8자, 최대 15자입니다.")
	private String password;

	public User toEntity() {
		return User.builder()
				.nickname(nickname)
				.permission(UserPermission.USER)
				.hashedPassword(password != null ? PasswordEncoderUtil.encode(password) : null)
				.build();
	}

	public User toEntity(UUID id) {
		return User.builder()
				.id(id)
				.nickname(nickname)
				.hashedPassword(password != null ? PasswordEncoderUtil.encode(password) : null)
				.build();
	}

}
