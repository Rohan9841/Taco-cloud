package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TacoCloudApplication {

	//this is the method that will run when the JAR file is executed
  public static void main(String[] args) {
    SpringApplication.run(TacoCloudApplication.class, args);
  }
  
}