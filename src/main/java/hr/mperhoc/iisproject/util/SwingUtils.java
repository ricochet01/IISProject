package hr.mperhoc.iisproject.util;

import java.io.File;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class SwingUtils {
	private SwingUtils() {
	}

	public static File chooseFile(String description, String... extensions) {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		fileChooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
		fileChooser.setDialogTitle("Choose file");
		fileChooser.setApproveButtonText("Select");
		fileChooser.setApproveButtonToolTipText("Select");
		fileChooser.setAcceptAllFileFilterUsed(false);

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String filename = selectedFile.getName();
			String extension = filename.substring(filename.lastIndexOf(".") + 1);
			return selectedFile.exists() && Arrays.asList(extensions).contains(extension.toLowerCase()) ? selectedFile
					: null;
		}
		return null;
	}

	// Let the user decide where to save the file
	public static File createNewFile(String description, String... extensions) {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		fileChooser.setSelectedFile(new File("*.xml"));
		fileChooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
		fileChooser.setDialogTitle("Save file");
		fileChooser.setApproveButtonText("Save");
		fileChooser.setApproveButtonToolTipText("Save");
		fileChooser.setAcceptAllFileFilterUsed(false);

		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String filename = selectedFile.getName();
			String extension = filename.substring(filename.lastIndexOf(".") + 1);
			return Arrays.asList(extensions).contains(extension.toLowerCase()) ? selectedFile : null;
		}
		return null;
	}

}
