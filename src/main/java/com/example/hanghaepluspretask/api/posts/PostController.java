package com.example.hanghaepluspretask.api.posts;

import com.example.hanghaepluspretask.api.posts.dto.in.DeletePostRequestDto;
import com.example.hanghaepluspretask.common.CommonResponse;
import com.example.hanghaepluspretask.api.posts.dto.in.PostRequestDto;
import com.example.hanghaepluspretask.api.posts.dto.out.PostResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {
	private final PostService postService;

	PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping()
	ResponseEntity<CommonResponse<List<PostResponse>>> findAll() {
		List<PostResponse> postResponseList = postService.findAll()
				.stream()
				.map(PostResponse::new)
				.toList();

		CommonResponse<List<PostResponse>> response = CommonResponse.ok(postResponseList);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	ResponseEntity<CommonResponse<PostResponse>> findById(
			@PathVariable("id") UUID id
	) {
		PostResponse postResponse = new PostResponse(postService.findById(id));

		CommonResponse<PostResponse> response = CommonResponse.ok(postResponse);
		return ResponseEntity.ok(response);
	}

	@PostMapping()
	ResponseEntity<CommonResponse<PostResponse>> create(
			@Valid @RequestBody PostRequestDto postRequestDto
	) {
		Post createdPost = postService.create(postRequestDto.toEntity());
		PostResponse postResponse = new PostResponse(createdPost);

		CommonResponse<PostResponse> response = CommonResponse.ok(postResponse);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	ResponseEntity<CommonResponse<PostResponse>> update(
			@PathVariable("id") UUID id,
			@RequestBody PostRequestDto request
	) {
		Post updatedPost = postService.update(request.toEntity(id));
		PostResponse postResponse = new PostResponse(updatedPost);

		CommonResponse<PostResponse> response = CommonResponse.ok(postResponse);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<CommonResponse<?>> delete(
			@PathVariable("id") UUID id,
			@RequestBody DeletePostRequestDto request
	) {
		postService.delete(id, request.getPassword());

		CommonResponse<?> response = CommonResponse.deleted();
		return ResponseEntity.ok(response);
	}
}
