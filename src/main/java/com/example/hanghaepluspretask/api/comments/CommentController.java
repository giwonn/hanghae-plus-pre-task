//package com.example.hanghaepluspretask.api.comments;
//
//import com.example.hanghaepluspretask.api.posts.Post;
//import com.example.hanghaepluspretask.api.posts.PostService;
//import com.example.hanghaepluspretask.api.posts.dto.in.DeletePostRequestDto;
//import com.example.hanghaepluspretask.api.posts.dto.in.PostRequestDto;
//import com.example.hanghaepluspretask.api.posts.dto.out.PostResponse;
//import com.example.hanghaepluspretask.common.CommonResponse;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/comments")
//public class CommentController {
//	private final CommentService commentService;
//
//	CommentController(CommentService commentService) {
//		this.commentService = commentService;
//	}
//
//	@GetMapping
//	ResponseEntity<CommonResponse<List<PostResponse>>> findByArticleId(
//			@RequestParam ArticleType type,
//			@RequestParam UUID id
//	) {
//		List<CommentResponse> postResponseList = commentService.findByArticleId(type, id)
//				.stream()
//				.map(CommentResponse::new)
//				.toList();
//
//		CommonResponse<List<PostResponse>> response = CommonResponse.ok(postResponseList);
//		return ResponseEntity.ok(response);
//	}
//
//	@GetMapping("/{id}")
//	ResponseEntity<CommonResponse<PostResponse>> findById(
//			@PathVariable("id") UUID id
//	) {
//		PostResponse postResponse = new PostResponse(postService.findById(id));
//
//		CommonResponse<PostResponse> response = CommonResponse.ok(postResponse);
//		return ResponseEntity.ok(response);
//	}
//
//	@PostMapping()
//	ResponseEntity<CommonResponse<PostResponse>> create(
//			@Valid @RequestBody PostRequestDto postRequestDto
//	) {
//		Post createdPost = postService.create(postRequestDto.toEntity());
//		PostResponse postResponse = new PostResponse(createdPost);
//
//		CommonResponse<PostResponse> response = CommonResponse.ok(postResponse);
//		return ResponseEntity.ok(response);
//	}
//
//	@PutMapping("/{id}")
//	ResponseEntity<CommonResponse<PostResponse>> update(
//			@PathVariable("id") UUID id,
//			@RequestBody PostRequestDto request
//	) {
//		Post updatedPost = postService.update(request.toEntity(id));
//		PostResponse postResponse = new PostResponse(updatedPost);
//
//		CommonResponse<PostResponse> response = CommonResponse.ok(postResponse);
//		return ResponseEntity.ok(response);
//	}
//
//	@DeleteMapping("/{id}")
//	ResponseEntity<CommonResponse<?>> delete(
//			@PathVariable("id") UUID id,
//			@RequestBody DeletePostRequestDto request
//	) {
//		postService.delete(id, request.getPassword());
//
//		CommonResponse<?> response = CommonResponse.deleted();
//		return ResponseEntity.ok(response);
//	}
//}
