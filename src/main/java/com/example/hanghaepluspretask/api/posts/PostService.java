package com.example.hanghaepluspretask.api.posts;

import com.example.hanghaepluspretask.util.PasswordEncoderUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {

	private final PostRepository postRepository;

	PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public List<Post> findAll() {
		return postRepository.findByDeletedAtIsNull();
	}

	public Post create(Post post) {
		return postRepository.save(post);
	}

	public Post findById(UUID id) {
		return postRepository.getByIdAndDeletedAtIsNull(id);
	}

	public Post update(Post post) {
		return postRepository.save(post);
	}

	@Transactional
	public void delete(UUID id, String password) {
		Post post = postRepository.getByIdAndDeletedAtIsNull(id);
		if (!PasswordEncoderUtil.matches(password, post.getHashedPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		post.softDelete();
		postRepository.save(post);
	}
}
