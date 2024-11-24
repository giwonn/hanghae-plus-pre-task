package com.example.hanghaepluspretask.api.posts.dto.out;

import com.example.hanghaepluspretask.api.posts.AuthorType;
import com.example.hanghaepluspretask.api.posts.Post;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.UUID;

public class PostResponse {

	private UUID id;

	private String title;

	private String content;

	private AuthorType authorType;

	private String nickname;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "UTC")
	private Instant createdAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "UTC")
	private Instant updatedAt;

	public PostResponse(Post post) {
		id = post.getId();
		title = post.getTitle();
		content = post.getContent();
		authorType = post.getAuthorType();
		nickname = post.getAuthorType() == AuthorType.USER ? post.getUser().getNickname() : post.getNickname();
		createdAt = post.getCreatedAt();
		updatedAt = post.getUpdatedAt();
	}
}
