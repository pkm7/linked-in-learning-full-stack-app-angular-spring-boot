package com.linkedin.learning.linkedinlearningfullstackappangularspringboot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.linkedin.learning.LinkedInLearningFullStackAppAngularSpringBootApplication;
import com.linkedin.learning.common.ResourceConststants;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = LinkedInLearningFullStackAppAngularSpringBootApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationResource {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() throws Exception {
		RestAssured.port = Integer.valueOf(port);
		RestAssured.basePath = ResourceConststants.ROOM_RESERVATION_V1;
		RestAssured.baseURI = "http://localhost";
	}

	@Test
	public void test_200() {
		given()
			.when()
				.get("/" + 1)
			.then()
				.statusCode(200);
	}
	
	@Test
	public void test_400() {
		given()
		.when()
		.get("/" + "AAAB" )
		.then()
		.statusCode(400);
	}


}
