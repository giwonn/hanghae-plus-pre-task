package com.example.hanghaepluspretask.api.users;

import com.example.hanghaepluspretask.api.users.dto.in.UserLoginRequest;

public interface LoginStrategy {
	User login(UserLoginRequest userLoginRequest);
	UserType getType();
}
