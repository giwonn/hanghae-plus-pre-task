package com.example.hanghaepluspretask.api.users.dto.out;

import com.example.hanghaepluspretask.api.users.User;
import com.example.hanghaepluspretask.api.users.UserPermission;

import java.util.UUID;

public class UserResponse {
	private UUID id;

	private String nickname;

	private UserPermission permission;

	public UserResponse(User user) {
		id = user.getId();
		nickname = user.getNickname();
		permission = user.getPermission();
	}

}
