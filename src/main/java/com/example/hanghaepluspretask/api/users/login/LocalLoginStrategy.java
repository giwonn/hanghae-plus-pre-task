package com.example.hanghaepluspretask.api.users.login;

import com.example.hanghaepluspretask.api.users.LoginStrategy;
import com.example.hanghaepluspretask.api.users.User;
import com.example.hanghaepluspretask.api.users.UserRepository;
import com.example.hanghaepluspretask.api.users.UserType;
import com.example.hanghaepluspretask.api.users.dto.in.UserLoginRequest;
import com.example.hanghaepluspretask.util.PasswordEncoderUtil;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LocalLoginStrategy implements LoginStrategy {

	private final UserRepository userRepository;

	LocalLoginStrategy(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User login(UserLoginRequest userLoginRequest) {
		Optional<User> foundUser = userRepository.findByNicknameAndDeletedAtIsNotNull(userLoginRequest.getNickname());

		if (foundUser.isEmpty() || !PasswordEncoderUtil.matches(userLoginRequest.getRawPassword(), foundUser.get().getHashedPassword())) {
			throw new IllegalArgumentException("로그인에 실패하였습니다. ID 또는 PW를 확인해주세요.");
		}

		return foundUser.get();
	}

	public UserType getType() {
		return UserType.LOCAL;
	}
}
