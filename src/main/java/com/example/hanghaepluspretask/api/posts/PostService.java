package com.example.hanghaepluspretask.api.posts;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
	private final PostRepository postRepository;

	PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	List<Post> findAll() {
		return postRepository.findAll();
	}

	Post create(Post post) {
		return postRepository.save(post);
	}

	// TODO
	Post findById(UUID id) {
		return postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	Post update(Post post) {
		return postRepository.save(post);
	}

	// TODO: 비밀번호 받아서 비교
	void delete(UUID id) {
		// TODO: Soft Delete로 변경 예정
		postRepository.deleteById(id);
	}
}
