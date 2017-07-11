package ua.rozhkov.filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManager {
	
	public static int calculateDirs(String path) throws IOException {
		int dirsCounter = 0;
		File[] tempFilesList;
		File temp;
		
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
	
	public static int calculateFiles(String path) throws IOException {
		
		File temp;
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
	
	public static boolean copy(String from, String to) throws IOException {
		boolean status = false;
		
		if ((new File(from).exists())) {
			if ((new File(from).isFile())) {
				
				new File(to).mkdirs();
				File toFile = new File(to + '/' + new File(from).getName());
				toFile.createNewFile();
				FileInputStream fileInputStream = new FileInputStream(from);
				FileOutputStream fileOutputStream = new FileOutputStream(toFile);
				while (fileInputStream.available() > 0) {
					fileOutputStream.write(fileInputStream.read());
				}
				
				fileInputStream.close();
				fileOutputStream.close();
				status = true;
			} else {
				new File(to).mkdir();
				File[] tempFileList;
				tempFileList = (new File(from).listFiles());
				new File(to + '/' + from).mkdir();
				for (File file : tempFileList) {
					copy(file.getPath(), to + '/' + new File(from).getName());
				}
				status = true;
			}
		}
		
		
		return status;
	}
	
	public static boolean move(String from, String to) throws IOException {
		boolean status = false;
		copy(from, to);
		if (new File(from).isFile()) {
			new File(from).delete();
			status = true;
		} else {
			removeAll(from);
			new File(from).delete();
			status = true;
		}
		return status;
	}
	
	protected static void removeAll(String path) {
		if (new File(path).isDirectory()) {
			for (File element : new File(path).listFiles()) {
				if (element != null) {
					if (element.isFile()) {
						element.delete();
					} else {
						removeAll(element.getPath());
					}
					element.delete();
				}
			}
		}
	}
	
}
