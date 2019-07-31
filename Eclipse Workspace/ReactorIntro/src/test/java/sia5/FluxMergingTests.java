package sia5;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

public class FluxMergingTests {

	@Test
	public void mergeFluxes() {
		Flux<String> characterFlux = Flux.just("Rohan","Shirish","Sunesh")
				.delayElements(Duration.ofMillis(500));
		
		Flux<String> foodFlux = Flux.just("Apple","Orange","Grape")
				.delaySubscription(Duration.ofMillis(250))
				.delayElements(Duration.ofMillis(500));
		
		Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux);
		
		StepVerifier.create(mergedFlux)
			.expectNext("Rohan")
			.expectNext("Apple")
			.expectNext("Shirish")
			.expectNext("Orange")
			.expectNext("Sunesh")
			.expectNext("Grape")
			.verifyComplete();
	}
	
	@Test
	public void zipFluxes() {
		Flux<String> characterFlux = Flux.just("Rohan","Shirish","Sunesh");
		
		Flux<String> foodFlux = Flux.just("Apple","Orange","Grape");
		
		Flux<Tuple2<String,String>> zippedFlux = Flux.zip(characterFlux, foodFlux);
		
		StepVerifier.create(zippedFlux)
			.expectNextMatches(p->
					p.getT1().equals("Rohan")&&
					p.getT2().equals("Apple")
					)
			.expectNextMatches(p->
					p.getT1().equals("Shirish")&&
					p.getT2().equals("Orange")
					)
			.expectNextMatches(p->
					p.getT1().equals("Sunesh")&&
					p.getT2().equals("Grape")
					)
			.verifyComplete();
			
	}
	
	@Test
	public void zipFluxesToObject() {
		Flux<String> characterFlux = Flux.just("Rohan","Shirish","Sunesh");
		
		Flux<String> foodFlux = Flux.just("Apple","Orange","Grape");
		
		Flux<String> zippedFlux = Flux.zip(characterFlux, foodFlux,(c,f)->c+" eats "+f);
		
		StepVerifier.create(zippedFlux)
			.expectNext("Rohan eats Apple")
			.expectNext("Shirish eats Orange")
			.expectNext("Sunesh eats Grape")
			.verifyComplete();
	}
	
	@Test
	public void firstFlux() {
		Flux<String> slowFlux = Flux.just("tortoise","snail","sloth")
					.delaySubscription(Duration.ofMillis(100));
		
		Flux<String> fastFlux = Flux.just("hare","cheetah","squirrel");
		
		Flux<String> firstFlux = Flux.first(slowFlux,fastFlux);
		
		StepVerifier.create(firstFlux)
			.expectNext("hare")
			.expectNext("cheetah")
			.expectNext("squirrel")
			.verifyComplete();
	}
}
