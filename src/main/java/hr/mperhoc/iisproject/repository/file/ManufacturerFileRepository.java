package hr.mperhoc.iisproject.repository.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;

import hr.mperhoc.iisproject.model.Manufacturer;
import hr.mperhoc.iisproject.model.list.ManufacturerList;
import hr.mperhoc.iisproject.repository.ManufacturerRepository;
import hr.mperhoc.iisproject.util.FileUtils;

public class ManufacturerFileRepository implements ManufacturerRepository {
	private static final String DIRECTORY_PATH = System.getProperty("user.home") + "\\iisproject";
	private static final String FILE_NAME = "manufacturers.bin";
	private static final String FILE_PATH = Paths.get(DIRECTORY_PATH, FILE_NAME).toString();

	private boolean readFile;
	private ManufacturerList manufacturers;

	public ManufacturerFileRepository() {
		manufacturers = new ManufacturerList();
	}

	private ManufacturerList read() {
		if (readFile || !FileUtils.fileExists(FILE_PATH)) return manufacturers;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
			readFile = true;
			return (ManufacturerList) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ManufacturerList();
		}
	}

	private void write() {
		FileUtils.writeObjectToFile(manufacturers, FILE_PATH);
	}

	@Override
	public void add(Manufacturer m) {
		manufacturers = read();
		manufacturers.add(m);
		write();
	}

	@Override
	public void update(int id, Manufacturer m) {
		manufacturers = read();
		manufacturers.update(id, m);
		write();
	}

	@Override
	public void delete(int id) {
		manufacturers = read();
		manufacturers.remove(id);
		write();
	}

	@Override
	public Manufacturer get(int id) {
		manufacturers = read();
		return manufacturers.get(id);
	}

	@Override
	public ManufacturerList getAll() {
		manufacturers = read();
		return manufacturers;
	}
}
