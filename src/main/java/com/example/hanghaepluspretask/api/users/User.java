package com.example.hanghaepluspretask.api.users;

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
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false, unique = true)
	private String nickname;

	@Column(nullable = false)
	private String hashedPassword;

	@Column(nullable = false)
	private UserPermission permission;

	public User() {}

}
