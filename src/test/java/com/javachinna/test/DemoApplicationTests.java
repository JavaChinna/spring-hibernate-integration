package com.javachinna.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.javachinna.model.UserDTO;
import com.javachinna.repo.UserInfoRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class })
public class DemoApplicationTests {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Test
	public void getUserInfoTest() {
		List<UserDTO> list = userInfoRepository.getUerInfo();
		Assertions.assertNotNull(list);
	}
}