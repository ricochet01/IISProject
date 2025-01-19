package hr.mperhoc.iisproject.repository;

import java.util.List;

import hr.mperhoc.iisproject.model.User;

public interface UserRepository {

	void add(User user);

	void update(int id, User user);

	void delete(int id);

	User get(int id);

	List<User> getAll();

}
