package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Identifies this class as a component for component scanning
@Controller
public class HomeController {
	
	//if an http get request is received for the root path /, then this method will handle the //request
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
}
