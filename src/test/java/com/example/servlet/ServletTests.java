package com.example.servlet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("MyServletTestClass")
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@AutoConfigureMockRestServiceServer(enabled = false)
@SpringBootTest(
	classes = ServletTests.MyServletTestConfig.class,
	properties = {
		"management.endpoints.web.exposure.include=*"
	})
public class ServletTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void myTest() throws Exception {
		this.mockMvc.perform(get("/actuator/info"))
			.andExpect(status().isOk());
	}

	@SpringBootConfiguration
	@EnableAutoConfiguration
	@Profile("MyServletTestClass")
	static class MyServletTestConfig {

	}
}