package com.test.demosrv;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

import reactor.test.StepVerifier;

public class CheckTest {
	
	@Test
	public void fluxTest() {
		Flux<String> strFlux = Flux.just("Test1","Test2","Test3")
				/*
				 * .concatWith(Flux.error(new RuntimeException("Connect with sub and errorr")))
				 */
				.concatWith(Flux.just("After Error"))
				.log();
		strFlux
			.subscribe(System.out :: println, 
					e -> System.err.println(e),
					() -> System.out.println());
	}
	
	@Test
	public void fluxTestElements_WithoutError() {
		Flux<String> strFlux = Flux.just("Test1","Test2","Test3")
				.log();
		
		StepVerifier
			.create(strFlux)
				.expectNext("Test1")
				.expectNext("Test2")
				.expectNext("Test3")
				.verifyComplete();
	}
	
	@Test
	public void fluxTestElements_WithoutError1() {
		Flux<String> strFlux = Flux.just("Test1","Test2","Test3")
				.log();
		
		StepVerifier
			.create(strFlux)
				.expectNext("Test1","Test2","Test3")
				.verifyComplete();
	}
	
	@Test
	public void fluxTestElements_WithError() {
		Flux<String> strFlux = Flux.just("Test1","Test2","Test3")
				.concatWith(Flux.error(new RuntimeException("Connect with sub and error")))
				.log();
		
		StepVerifier
			.create(strFlux)
				.expectNext("Test1")
				.expectNext("Test2")
				.expectNext("Test3")
				//.expectError(RuntimeException.class)
				.expectErrorMessage("Connect with sub and error")
				.verify();
	}
	
}
