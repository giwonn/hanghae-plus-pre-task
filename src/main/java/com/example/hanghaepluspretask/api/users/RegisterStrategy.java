package com.example.hanghaepluspretask.api.users;

public interface RegisterStrategy {
	void register(User user);
	UserType getType();
}
