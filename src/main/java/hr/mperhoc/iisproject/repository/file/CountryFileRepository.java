package hr.mperhoc.iisproject.repository.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;

import hr.mperhoc.iisproject.model.Country;
import hr.mperhoc.iisproject.model.list.CountryList;
import hr.mperhoc.iisproject.repository.CountryRepository;
import hr.mperhoc.iisproject.util.FileUtils;

public class CountryFileRepository implements CountryRepository {
	private static final String DIRECTORY_PATH = System.getProperty("user.home") + "\\iisproject";
	private static final String FILE_NAME = "countries.bin";
	private static final String FILE_PATH = Paths.get(DIRECTORY_PATH, FILE_NAME).toString();

	private boolean readFile;
	private CountryList countries;

	public CountryFileRepository() {
		countries = new CountryList();
	}

	private CountryList read() {
		if (readFile || !FileUtils.fileExists(FILE_PATH)) return countries;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
			readFile = true;
			return (CountryList) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new CountryList();
		}
	}

	private void write() {
		FileUtils.writeObjectToFile(countries, FILE_PATH);
	}

	@Override
	public void add(Country c) {
		countries = read();
		countries.add(c);
		write();
	}

	@Override
	public void update(int id, Country c) {
		countries = read();
		countries.update(id, c);
		write();
	}

	@Override
	public void delete(int id) {
		countries = read();
		countries.remove(id);
		write();
	}

	@Override
	public Country get(int id) {
		countries = read();
		return countries.get(id);
	}

	@Override
	public CountryList getAll() {
		countries = read();
		return countries;
	}

}
