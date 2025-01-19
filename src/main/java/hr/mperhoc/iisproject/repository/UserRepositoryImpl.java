package hr.mperhoc.iisproject.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hr.mperhoc.iisproject.model.User;

public class UserRepositoryImpl implements UserRepository {
	private Map<Integer, User> users;

	public UserRepositoryImpl() {
		users = new TreeMap<>();
	}

	@Override
	public void add(User user) {
		users.put(user.getId(), user);
	}

	@Override
	public void update(int id, User user) {
		users.put(id, user);
	}

	@Override
	public void delete(int id) {
		users.remove(id);
	}

	@Override
	public User get(int id) {
		return users.get(id);
	}

	@Override
	public List<User> getAll() {
		return new ArrayList<>(users.values());
	}
}
