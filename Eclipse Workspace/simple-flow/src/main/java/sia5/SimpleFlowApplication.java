package sia5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleFlowApplication.class, args);
	}

	@Bean
	public CommandLineRunner writeData(FileWriterGateway gateway) {
		return args -> {
			gateway.writeToFile("simple.txt","Hello, spring integration is working");
		};
	}
}
