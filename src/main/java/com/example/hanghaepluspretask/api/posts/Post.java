package com.example.hanghaepluspretask.api.posts;

import com.example.hanghaepluspretask.api.users.User;
import com.example.hanghaepluspretask.common.base.BaseEntity;
import com.example.hanghaepluspretask.util.PasswordEncoderUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Post extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AuthorType authorType;

	@Column(name = "userId", insertable = false, updatable = false)
	private UUID userId;

	private String nickname;

	private String hashedPassword;

	// TODO: fetch를 무조건 하는게 정말 최선일까?
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;

	public Post() {}
}
