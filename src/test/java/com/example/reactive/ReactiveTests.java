package com.example.reactive;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("MyReactiveTestClass")
@AutoConfigureWebTestClient
@SpringBootTest(
	classes = ReactiveTests.MyReactiveTestConfig.class,
	properties = {
		"spring.main.web-application-type=reactive",
		"management.endpoints.web.exposure.include=*"
	})
public class ReactiveTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void myTest() {
		this.webTestClient.get()
			.uri("http://localhost/actuator/info")
			.exchange()
			.expectStatus().isOk();
	}

	@SpringBootConfiguration
	@EnableAutoConfiguration
	@Profile("MyReactiveTestClass")
	static class MyReactiveTestConfig {

	}
}