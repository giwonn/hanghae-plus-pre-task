package com.example.hanghaepluspretask.api.posts;

import com.example.hanghaepluspretask.api.common.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/posts")
public class PostController {
	private final PostService postService;

	PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/")
	ResponseEntity<CommonResponse<List<PostResponse>>> findAll() {
		List<PostResponse> postResponseList = postService.findAll().stream().map(PostResponse::new).toList();
		CommonResponse<List<PostResponse>> response = CommonResponse.ok(postResponseList);

		return ResponseEntity.ok(response);
	}
}
