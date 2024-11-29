package com.example.hanghaepluspretask.api.users.register;

import com.example.hanghaepluspretask.api.users.RegisterStrategy;
import com.example.hanghaepluspretask.api.users.User;
import com.example.hanghaepluspretask.api.users.UserRepository;
import com.example.hanghaepluspretask.api.users.UserType;

import java.util.Optional;

public class LocalRegisterStrategy implements RegisterStrategy {

	private final UserRepository userRepository;

	LocalRegisterStrategy(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void register(User user) {
		Optional<User> foundUser = userRepository.findByNickname(user.getNickname());

		if (foundUser.isPresent()) {
			throw new IllegalArgumentException("이미 가입된 이메일입니다.");
		}

		userRepository.save(user);
	}

	public UserType getType() {
		return UserType.LOCAL;
	}
}
