package com.example.hanghaepluspretask.api.users;

import com.example.hanghaepluspretask.common.base.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User, UUID> {

	Optional<User> findByNickname(String nickname);

	Optional<User> findByNicknameAndDeletedAtIsNotNull(String nickname);
}
