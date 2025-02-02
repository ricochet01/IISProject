package hr.mperhoc.iisproject.repository.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import hr.mperhoc.iisproject.model.User;
import hr.mperhoc.iisproject.repository.UserRepository;
import hr.mperhoc.iisproject.util.FileUtils;

public class UserFileRepository implements UserRepository {
	private static final String DIRECTORY_PATH = System.getProperty("user.home") + "\\iisproject";
	private static final String FILE_NAME = "users.bin";
	private static final String FILE_PATH = Paths.get(DIRECTORY_PATH, FILE_NAME).toString();

	private boolean readFile;
	private Map<Integer, User> users = new TreeMap<>();

	@SuppressWarnings("unchecked")
	private Map<Integer, User> read() {
		if (readFile || !FileUtils.fileExists(FILE_PATH)) return users;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
			readFile = true;
			return (Map<Integer, User>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new TreeMap<Integer, User>();
		}
	}

	private void write() {
		FileUtils.writeObjectToFile(users, FILE_PATH);
	}

	@Override
	public void add(User user) {
		users = read();
		users.put(user.getId(), user);
		write();
	}

	@Override
	public void update(int id, User user) {
		users = read();
		users.put(id, user);
		write();
	}

	@Override
	public void delete(int id) {
		users = read();
		users.remove(id);
		write();
	}

	@Override
	public User get(int id) {
		users = read();
		return users.get(id);
	}

	@Override
	public List<User> getAll() {
		users = read();
		return new ArrayList<>(users.values());
	}

}
