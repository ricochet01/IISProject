package hr.mperhoc.iisproject.repository.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;

import hr.mperhoc.iisproject.model.Food;
import hr.mperhoc.iisproject.model.list.FoodList;
import hr.mperhoc.iisproject.repository.FoodRepository;
import hr.mperhoc.iisproject.util.FileUtils;

public class FoodFileRepository implements FoodRepository {
	private static final String DIRECTORY_PATH = System.getProperty("user.home") + "\\iisproject";
	private static final String FILE_NAME = "foods.bin";
	private static final String FILE_PATH = Paths.get(DIRECTORY_PATH, FILE_NAME).toString();

	private boolean readFile;
	private FoodList foods;

	public FoodFileRepository() {
		foods = new FoodList();
	}

	private FoodList read() {
		if (readFile || !FileUtils.fileExists(FILE_PATH)) return foods;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
			readFile = true;
			return (FoodList) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new FoodList();
		}
	}

	private void write() {
		FileUtils.writeObjectToFile(foods, FILE_PATH);
	}

	@Override
	public void add(Food f) {
		foods = read();
		foods.add(f);
		write();
	}

	@Override
	public void update(int id, Food f) {
		foods = read();
		foods.update(id, f);
		write();
	}

	@Override
	public void delete(int id) {
		foods = read();
		foods.remove(id);
		write();
	}

	@Override
	public Food get(int id) {
		foods = read();
		return foods.get(id);
	}

	@Override
	public FoodList getAll() {
		foods = read();
		return foods;
	}

}
