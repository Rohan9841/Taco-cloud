package sia5;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;

import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

public class FluxTransformingTest {

	@Test
	public void skipAfew() {
		Flux<String> skipFlux = Flux.just("one","two","skip a few","ninety","hundred").skip(3);
		
		StepVerifier.create(skipFlux)
			.expectNext("ninety")
			.expectNext("hundred")
			.verifyComplete();
	}
	
	@Test 
	public void skipAfewSeconds() {
		Flux<String> skipFlux = Flux.just("one","two","skip a few","ninety","hundred")
				.delayElements(Duration.ofSeconds(1))
				.skip(Duration.ofSeconds(4));
		
		StepVerifier.create(skipFlux)
		.expectNext("ninety")
		.expectNext("hundred")
		.verifyComplete();
	}
	
	@Test
	public void take() {
		Flux<String> takeFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon",
				 "Zion", "Grand Teton").take(3);
		
		StepVerifier.create(takeFlux)
			.expectNext("Yellowstone")
			.expectNext("Yosemite")
			.expectNext("Grand Canyon")
			.verifyComplete();
	}
	
	@Test
	public void takeDuringSeconds() {
		Flux<String> takeFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon",
				 "Zion", "Grand Teton")
				.delayElements(Duration.ofSeconds(1))
				.take(Duration.ofSeconds(4));
		
		StepVerifier.create(takeFlux)
			.expectNext("Yellowstone")
			.expectNext("Yosemite")
			.expectNext("Grand Canyon")
			.verifyComplete();
	}
	
	@Test
	public void filter() {
		Flux<String> filterFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon",
				 "Zion", "Grand Teton")
				.filter(n->!n.contains(" "));
		
		StepVerifier.create(filterFlux)
			.expectNext("Yellowstone")
			.expectNext("Yosemite")
			.expectNext("Zion")
			.verifyComplete();
	}
	
	@Test
	public void distinct() {
		Flux<String> distinctFlux = Flux.just("dog","cat","dog","bird","ant")
				.distinct();
		
		StepVerifier.create(distinctFlux)
			.expectNext("dog")
			.expectNext("cat")
			.expectNext("bird")
			.expectNext("ant")
			.verifyComplete();
	}
	
	@Test
	public void map() {
		Flux<Player> mapFlux = Flux.just("Michael Jordan","Scott Pippen","Stever Curr")
				.map(p->{
					String[] arr = p.split("\\s");
					return new Player(arr[0],arr[1]);
				});
		
		StepVerifier.create(mapFlux)
			.expectNext(new Player("Michael","Jordan"))
			.expectNext(new Player("Scott", "Pippen"))
			.expectNext(new Player("Stever", "Curr"))
			.verifyComplete();
	}
	
	@Test
	public void flatMap() {
		Flux<Player> flatMapFlux = Flux.just("Michael Jordan","Scott Pippen","Steven Curr")
				.flatMap(n->Mono.just(n)
						.map(p->{
							String[] arr = p.split("\\s");
							return new Player(arr[0],arr[1]);
							})
						.subscribeOn(Schedulers.parallel())
						);
		
		List<Player> playerList = new ArrayList<>();
				playerList.add(new Player("Michael", "Jordan"));
				playerList.add(new Player("Scott", "Pippen"));
				playerList.add(new Player("Steven", "Curr"));
		
		StepVerifier.create(flatMapFlux)
			.expectNextMatches(n->playerList.contains(n))
			.expectNextMatches(n->playerList.contains(n))
			.expectNextMatches(n->playerList.contains(n))
			.verifyComplete();
		
	}
	
	@Data
	private static class Player{
		private final String firstName;
		private final String lastName;
	}
	
}
