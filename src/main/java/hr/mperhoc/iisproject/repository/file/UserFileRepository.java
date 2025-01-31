package hr.mperhoc.iisproject.repository.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import hr.mperhoc.iisproject.model.User;
import hr.mperhoc.iisproject.repository.UserRepository;
import hr.mperhoc.iisproject.util.FileUtils;

public class UserFileRepository implements UserRepository {
	private static final String DIRECTORY_PATH = System.getProperty("user.home") + "\\iisproject";
	private static final String FILE_NAME = "users.bin";
	public static final String FILE_PATH = Paths.get(DIRECTORY_PATH, FILE_NAME).toString();

	private Map<Integer, User> users;

	@SuppressWarnings("unchecked")
	@Override
	public void add(User user) {
		if (FileUtils.fileExists(FILE_PATH)) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
				users = (Map<Integer, User>) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(int id, User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
