package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
		
	/*
	 * Configure password encoder for JDBC-based authentication to encode the
	 * password in the database.
	 */
		@Bean
		public PasswordEncoder encoder() {
			return new BCryptPasswordEncoder();
		}
		
	/*
	 * Specify that request of "/design" and "/orders" should be for users with
	 * granted authority "ROLE_USER" All other requests should be permitted to all
	 * users
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/design", "/orders")
					.access("hasRole('ROLE_USER')")
				.antMatchers("/", "/**").access("permitAll")
			
			.and()
			
				/*
				 * Specifies that custom login page will be at "/login" and after the login is
				 * successful always go to "/design" path
				 * 
				 * Also, when spring security finds that the user is unauthenticated and needs 
				 * to log in, it will redirect to /login page
				 */
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/design",true)
		
			/* Sets up security filter that intercepts POST requests to /logout 
			 * Once the logout is successful, the user will be sent to homepage
			 */
			.and()
				.logout()
					.logoutSuccessUrl("/")
		
		//make h2-console non-secured for debug purpose
			.and()
				.csrf()
					.ignoringAntMatchers("/h2-console/**")
					
		//Allows pages to be loaded in frames from the same origin; needed for H2-CONSOLE
			.and()
				.headers()
					.frameOptions()
						.sameOrigin();
			
	}
}
