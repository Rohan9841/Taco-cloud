package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.data.UserRepository;

/*Marks this class for component scanning*/
@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	/* The PasswordEncoder is the same bean that is declared in SecurityConfig */
	@Autowired
	public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder){
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping
	public String registrationForm(Model model) {
		return "registration";
	}
	
	/*
	 * The thymeleaf template 'registration.html' posts the form to this method. The input value in that form 
	 * is linked to the properties of RegistrationForm by name attribute.
	 */
	@PostMapping
	public String processRegistration(RegistrationForm form) {
		userRepo.save(form.toUser(passwordEncoder));
		return "redirect:/login";
		
	}
}
