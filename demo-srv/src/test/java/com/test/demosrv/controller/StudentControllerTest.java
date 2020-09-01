package com.test.demosrv.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.test.demosrv.model.Student;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@WebFluxTest
public class StudentControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	public void studentsTest() {
		Flux<Student> studentFlux = 
			webTestClient
			.get()
			.uri("/students")
			.exchange()
			.expectStatus().isOk()
			.returnResult(Student.class)
			.getResponseBody();
		
		StepVerifier.create(studentFlux)
					.expectNextCount(3)
					.verifyComplete();
	}
	
	@Test
	public void studentsTest2() {
		
			webTestClient
			.get()
			.uri("/students")
			.exchange()
			.expectStatus().isOk()
			.expectHeader()
			.contentType(MediaType.APPLICATION_STREAM_JSON_VALUE)
			.expectBodyList(Student.class)
			.hasSize(3);
		
	}
	
	@Test
	public void studentsTest3() {
		
		EntityExchangeResult<List<Student>> actualStudentList = webTestClient
			.get()
			.uri("/students")
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(Student.class)
			.returnResult();
		
		System.out.println(actualStudentList);
		
	}
}
