package tacos;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)

/*This is a special test annotation provided by Spring Boot that arranges for the test to run in
the context of a Spring MVC application.*/
@WebMvcTest(HomeController.class)
public class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	// defines the test you want to perform against the homepage.
	public void testHomePage() throws Exception {
		//performs an HTTP GET request for / (the root path)
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"))
				.andExpect(content().string(containsString("Welcome to...")));
	}
}
