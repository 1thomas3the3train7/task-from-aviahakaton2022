package com.example.tasksher;

import com.example.tasksher.Model.RefreshToken;
import com.example.tasksher.Model.User;
import com.example.tasksher.Repository.RefreshTokenRepository;
import com.example.tasksher.Repository.UserRepository;
import com.example.tasksher.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskSherApplicationTests {
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;


	@Test
	void contextLoads() {
	}
	@Test
	void forUser1(){
		User user = new User();
		user.setEmail("email");
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setRefreshToken("token");
		user.setToken(refreshToken);
		userRepository.save(user);

	}
	@Test
	void forUser2(){
		User user = userRepository.getUserAndRoleAndTokenByEmail("email");
		System.out.println(user.getEmail());
	}

}
