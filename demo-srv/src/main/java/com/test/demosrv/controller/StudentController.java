package com.test.demosrv.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.demosrv.model.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StudentController {
	
	public static List<Student> stds = new ArrayList<Student>();
	
	static {
		stds.add(Student.builder().sId(1).name("Pabi1").build());
		stds.add(Student.builder().sId(2).name("Pabi2").build());
		stds.add(Student.builder().sId(3).name("Pabi3").build());
	}
	
	@GetMapping(value = "/students", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Student> studetns(){
		return Flux.just(
						Student.builder().sId(1).name("Pabi1").build(),
						Student.builder().sId(2).name("Pabi2").build(),
						Student.builder().sId(3).name("Pabi3").build())
					.delayElements(Duration.ofSeconds(2))
					.log();
	}
	
	@GetMapping(value = "std/{status}",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Mono<ResponseEntity<List<Integer>>> getAllDetails(@PathVariable("status") String status ){
		Mono<List<Integer>> monoIds= getStdIds(status);
		return monoIds.map(ids -> ResponseEntity.ok(ids));
		
	}


	private Mono<List<Integer>> getStdIds(String status) {
		List<Integer> ids = stds.stream()
		.map(std -> std.getSId())
		.collect(Collectors.toList());
		return Mono.just(ids);
	}
	
}
