	package tacos.security;
	
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;
	import org.springframework.stereotype.Service;
	
	import tacos.User;
	import tacos.data.UserRepository;
	
	/*Stereotype annotation to flag this class for spring's component scanning*/
	
	@Service
	public class UserRepositoryUserDetailsService implements UserDetailsService {
	
		private UserRepository userRepo;	
		
	/* injected with instance of UserRepository */
		@Autowired
		public UserRepositoryUserDetailsService(UserRepository userRepo) {
			this.userRepo = userRepo;
		}
	
		/*
		 * called findByUsername() from userRepo to look up a user. The
		 * loadUserByUsername must never return null so if the method returns null then
		 * it will throw UsernameNotFoundExcpetion.
		 * 
		 * Althouh the return type is UserDetails, we are returning User because User
		 * implements UserDetails. And findByUsername() gives us User.
		 */
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user = userRepo.findByUsername(username);
			if(user != null) {
				return user;
			}
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}
	
	}
