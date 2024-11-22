package com.example.hanghaepluspretask.api.posts.dto.in;

import com.example.hanghaepluspretask.api.posts.AuthorType;
import com.example.hanghaepluspretask.api.posts.Post;
import com.example.hanghaepluspretask.util.PasswordEncoderUtil;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class PostRequestDto {

	@NotBlank(message = "제목은 필수 항목입니다.")
	@Size(max = 255, message = "제목은 최대 255자까지 입력 가능합니다.")
	private String title;

	@NotBlank(message = "내용은 필수 항목입니다.")
	@Size(max = 5000, message = "내용은 최대 5000자까지 입력 가능합니다.")
	private String content;

	@NotNull(message = "작성자 유형은 필수 항목입니다.")
	private AuthorType authorType;

	private UUID userId;

	private String nickname;

	private String password;

	@AssertTrue(message = "작성자 정보가 잘못되었습니다.")
	private boolean isValidUserInfo() {
		// java 12부터 가능한 switch 표현식
		return switch (authorType) {
			case USER -> userId != null;
			case GUEST -> nickname != null && password != null;
		};
	}

	public Post toEntity() {
		return Post.builder()
				.title(title)
				.content(content)
				.authorType(authorType)
				.userId(userId)
				.nickname(nickname)
				.hashedPassword(PasswordEncoderUtil.encode(password))
				.build();
	}

	public Post toEntity(UUID id) {
		return Post.builder()
				.id(id)
				.title(title)
				.content(content)
				.authorType(authorType)
				.userId(userId)
				.nickname(nickname)
				.hashedPassword(PasswordEncoderUtil.encode(password))
				.build();
	}

}
