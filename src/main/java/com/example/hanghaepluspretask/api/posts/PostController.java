package com.example.hanghaepluspretask.api.posts;

import com.example.hanghaepluspretask.api.common.CommonResponse;
import com.example.hanghaepluspretask.api.posts.dto.in.UpdatePostRequest;
import com.example.hanghaepluspretask.api.posts.dto.out.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
	private final PostService postService;

	PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping()
	ResponseEntity<CommonResponse<List<PostResponse>>> findAll() {
		List<PostResponse> postResponseList = postService.findAll().stream().map(PostResponse::new).toList();
		CommonResponse<List<PostResponse>> response = CommonResponse.ok(postResponseList);

		return ResponseEntity.ok(response);
	}

	@PostMapping()
	ResponseEntity<CommonResponse<PostResponse>> create() {
		PostResponse postResponse = new PostResponse(postService.create());
		CommonResponse<PostResponse> response = CommonResponse.ok(postResponse);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	ResponseEntity<CommonResponse<PostResponse>> create(@PathVariable("id") String id) {
		PostResponse postResponse = new PostResponse(postService.findById(id));
		CommonResponse<PostResponse> response = CommonResponse.ok(postResponse);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	ResponseEntity<CommonResponse<PostResponse>> update(
			@PathVariable("id") String id,
			@RequestBody UpdatePostRequest request
	) {
		PostResponse postResponse = new PostResponse(postService.update(id, request.toPost()));
		CommonResponse<PostResponse> response = CommonResponse.ok(postResponse);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<CommonResponse<PostResponse>> delete(@PathVariable("id") String id) {
		PostResponse postResponse = new PostResponse(postService.delete(id));
		CommonResponse<PostResponse> response = CommonResponse.ok(postResponse);

		return ResponseEntity.ok(response);
	}
}
