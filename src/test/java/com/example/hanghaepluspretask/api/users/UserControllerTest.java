//package com.example.hanghaepluspretask.api.users;
//
//import com.example.hanghaepluspretask.api.posts.Post;
//import com.example.hanghaepluspretask.api.posts.dto.out.PostResponse;
//import com.example.hanghaepluspretask.common.CommonResponse;
//import com.example.hanghaepluspretask.util.PasswordEncoderUtil;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//public class UserControllerTest {
//
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	@Autowired
//	private UserController userController;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	private MockMvc mockMvc;
//
//	private User testUser;
//
//	private final String testUserPassword = "testPassword";
//
//	@BeforeAll
//	void setUp() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}
//
//	void registerTestUser() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//
//		User user = User.builder()
//				.email("test1@gmail.com")
//				.nickname("testNickname")
//				.hashedPassword(testUserPassword)
//				.build();
//
//		testUser = userService.register(user);
//	}
//
//	void deleteTestUser() {
//		userService.delete(testUser.getId());
//	}
//
//	@Test
//	void successLogin() throws Exception {
//		User user = User.builder()
//				.email("test@gmail.com")
//				.nickname("testNickname")
//				.hashedPassword("testPassword")
//				.build();
//		LoginResponseDto loginResponseDto = new LoginResponseDto(user);
//		String expectedJson = objectMapper.writeValueAsString(loginResponseDto);
//		// TODO : 이거 어떻게 처리해야하지
//
//		mockMvc.perform(post("/user/login"))
//				.andExpect(status().isOk())
//				// ...
//				.andDo(print());
//
//		deleteTestUser();
//	}
//
//}
