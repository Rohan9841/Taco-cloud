package sia5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

public class FluxBufferingTest {

	@Test
	public void buffer() {
		Flux<String> fruitFlux = Flux.just("Apple","Orange","Grape","Banana","Kiwi");
		Flux<List<String>> bufferedFlux = fruitFlux.buffer(3);
		
		StepVerifier.create(bufferedFlux)
			.expectNext(Arrays.asList("Apple","Orange","Grape"))
			.expectNext(Arrays.asList("Banana","Kiwi"))
			.verifyComplete();
	}
	
	@Test
	  public void bufferAndFlatMap() throws Exception {
	    Flux.just(
	        "apple", "orange", "banana", "kiwi", "strawberry")
	        .buffer(3)
	        .flatMap(x -> 
	          Flux.fromIterable(x)
	            .map(y -> y.toUpperCase())
	            .subscribeOn(Schedulers.parallel())   
	            .log()
	        ).subscribe();
	  }
	
	@Test
	public void collectList() {
		Flux<String> fruitFlux = Flux.just("Apple","Orange","Grape","Banana","Kiwi");
		Mono<List<String>> collectListFlux = fruitFlux.collectList();
		
		StepVerifier.create(collectListFlux)
			.expectNext(Arrays.asList("Apple","Orange","Grape","Banana","Kiwi"))
			.verifyComplete();
	}
	
	@Test
	public void mapList() {
		Flux<String> animalFlux = Flux.just("aardvark","elephant","koala","eagle","kangaroo");
		Mono<Map<Character,String>> animalMapMono = animalFlux.collectMap(a->a.charAt(0)); 
		
		StepVerifier.create(animalMapMono)
			.expectNextMatches(m->{
				return 
						m.size() == 3 &&
						m.get('a').equals("aardvark") &&
						m.get('e').equals("eagle") &&
						m.get('k').equals("kangaroo");
			})
			.verifyComplete();
	}
	
	@Test
	public void all() {
		Flux<String> animalFlux = Flux.just("aardvark","elephant","koala","eagle","kangaroo");
		
		Mono<Boolean> hasAmono = animalFlux.all(a->a.contains("a"));
		StepVerifier.create(hasAmono)
			.expectNext(true)
			.verifyComplete();
		
		Mono<Boolean> hasKmono = animalFlux.all(a -> a.contains("k"));
		StepVerifier.create(hasKmono)
			.expectNext(false)
			.verifyComplete();
		
	}
	
	@Test
	public void any() {
		Flux<String> animalFlux = Flux.just("aardvark","elephant","koala","eagle","kangaroo");
		
		Mono<Boolean> hasAmono = animalFlux.any(a->a.contains("a"));
		StepVerifier.create(hasAmono)
			.expectNext(true)
			.verifyComplete();
		
		Mono<Boolean> hasKmono = animalFlux.any(a->a.contains("k"));
		
		StepVerifier.create(hasKmono)
			.expectNext(true)
			.verifyComplete();
		
	}
}

