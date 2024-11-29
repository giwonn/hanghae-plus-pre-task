package com.example.hanghaepluspretask.api.posts;

import com.example.hanghaepluspretask.api.posts.dto.out.PostResponse;
import com.example.hanghaepluspretask.common.CommonResponse;
import com.example.hanghaepluspretask.util.PasswordEncoderUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class PostControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private PostService postService;

	@Autowired
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	private Post guestPost;

	private final String testRawPassword = "test";

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		Post post = Post.builder()
				.title("Test Title")
				.content("Test Content")
				.authorType(AuthorType.GUEST)
				.userId(UUID.randomUUID())
				.nickname("Test Nickname")
				.hashedPassword(PasswordEncoderUtil.encode(testRawPassword))
				.build();

		guestPost = postService.create(post);
	}

	@AfterEach
	void tearDown() {
		postService.delete(guestPost.getId(), testRawPassword);
	}

	@Test
	void successLogin() throws Exception {
		List<Post> posts = postService.findAll(); // 데이터베이스에서 저장된 Post 조회
		List<PostResponse> expectedResponse = posts.stream()
				.map(PostResponse::new)
				.toList();
		String expectedJson = objectMapper.writeValueAsString(CommonResponse.ok(expectedResponse));

		mockMvc.perform(post("/posts"))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedJson))
				.andDo(print());
	}

}
