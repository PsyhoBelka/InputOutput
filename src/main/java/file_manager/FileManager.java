package file_manager;

import java.io.File;
import java.io.IOException;

public class FileManager {
	
	private static File temp;
	
	public static int calculateFiles(String path) throws IOException {
		int filesCounter = 0;
		File[] tempFilesList;
		
		temp = new File(path);
		if (temp.isDirectory()) {
			tempFilesList = temp.listFiles();
			if (tempFilesList != null) {
				for (File aTempFilesList : tempFilesList) {
					if (!aTempFilesList.isDirectory()) {
						filesCounter++;
					}
				}
				for (File aTempFilesList : tempFilesList) {
					if (aTempFilesList.isDirectory()) {
						filesCounter += calculateFiles(aTempFilesList.getPath());
					}
				}
			}
		} else {
			throw new IOException("No directory for given path");
		}
		
		return filesCounter;
	}
	
	public static int calculateDirs(String path) throws IOException {
		int dirsCounter = 0;
		File[] tempFilesList;
		
		temp = new File(path);
		if (temp.isDirectory()) {
			tempFilesList = temp.listFiles();
			if (tempFilesList != null) {
				for (File aTempFilesList : tempFilesList) {
					if (aTempFilesList.isDirectory()) {
						dirsCounter++;
					}
				}
				for (File aTempFilesList : tempFilesList) {
					if (aTempFilesList.isDirectory()) {
						dirsCounter += calculateDirs(aTempFilesList.getPath());
					}
				}
			}
		} else {
			throw new IOException("No directory for given path");
		}
		return dirsCounter;
	}
	
	public static boolean copy(String from, String to) throws IOException {
		return new File(from).renameTo(new File(to));
	}
	
	public static boolean move(String from, String to) throws IOException {
		boolean status = copy(from, to);
		return status && new File(from).delete();
	}
	
	
}
