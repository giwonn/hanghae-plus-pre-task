package com.example.hanghaepluspretask.api.users;

import com.example.hanghaepluspretask.api.users.dto.in.UserLoginRequest;
import com.example.hanghaepluspretask.providers.JwtProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

	private final Map<UserType, LoginStrategy> loginStrategyMap;
	private final Map<UserType, RegisterStrategy> registerStrategyMap;
	private final JwtProvider jwtProvider;

	UserService(
			List<LoginStrategy> loginStrategies,
			List<RegisterStrategy> registerStrategies,
			JwtProvider jwtProvider
	) {
		this.loginStrategyMap = loginStrategies.stream()
				.collect(Collectors.toMap(
						LoginStrategy::getType,
						strategy -> strategy
				));
		this.registerStrategyMap = registerStrategies.stream()
				.collect(Collectors.toMap(
						RegisterStrategy::getType,
						strategy -> strategy
				));
		this.jwtProvider = jwtProvider;
	}

	// TODO: auth로 구현해야하는데 user에 구현해버렸네...
	public String login(UserLoginRequest loginRequest) {
		LoginStrategy loginStrategy = loginStrategyMap.get(loginRequest.getUserType());
		if (loginStrategy == null) {
			throw new BadCredentialsException("로그인에 실패하였습니다.");
		}

		User user = loginStrategy.login(loginRequest);

		return jwtProvider.createToken(user.getId(), 3600);
	}

	public void register(User user, UserType userType) {
		RegisterStrategy registerStrategy = registerStrategyMap.get(userType);
		if (registerStrategy == null) {
			throw new BadCredentialsException("회원가입에 실패하였습니다.");
		}

		registerStrategy.register(user);
	}


}
