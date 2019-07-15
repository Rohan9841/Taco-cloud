package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.User;

public interface UserRepository extends CrudRepository<User, Long> {

	/*
	 * This method will be used in the user details service to look up a User by
	 * their username
	 */
	User findByUsername(String username);
}
