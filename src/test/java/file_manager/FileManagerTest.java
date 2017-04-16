package file_manager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileManagerTest {
	
	private String path = "src/test/resources/file_manager/dirs2";
	private List dirsList;
	private List filesList;
	
	@Before
	public void before() throws IOException {
		new File(path).mkdirs();
		dirsList = Arrays.asList(
				new File(path + "/dir1"),
				new File(path + "/dir1/subdir1-1"),
				new File(path + "/dir1/subdir1-2"),
				new File(path + "/dir1/subdir1-3"),
				
				new File(path + "/dir2"),
				new File(path + "/dir2/subdir2-1"),
				
				new File(path + "/dir3"),
				new File(path + "/dir3/subdir3-1")
		);
		
		filesList = Arrays.asList(
				new File(path + "/dir1/file1-1.txt"),
				new File(path + "/dir1/file1-2.txt"),
				new File(path + "/dir1/file1-3.txt"),
				new File(path + "/dir1/subdir1-1/file1-1.txt"),
				new File(path + "/dir1/subdir1-1/file1-2.txt"),
				new File(path + "/dir1/subdir1-1/file1-3.txt"),
				
				new File(path + "/dir2/file2-1.txt"),
				new File(path + "/dir2/file2-2.txt"),
				
				new File(path + "/dir3/file3-1.txt"),
				new File(path + "/dir3/file3-2.txt")
		);
		
		for (Object dir : dirsList) {
			((File) dir).mkdir();
		}
		
		for (Object file : filesList) {
			((File) file).createNewFile();
		}
	}
	
	@After
	public void after() {
		removeAll(path);
		new File(path).delete();
	}
	
	@Test
	public void testCalculateFiles() throws Exception {
		assertEquals(10, FileManager.calculateFiles(path));
	}
	
	@Test
	public void testCalculateDirs() throws Exception {
		assertEquals(8, FileManager.calculateDirs(path));
	}
	
	@Test
	public void testCopy() throws Exception {
		FileManager.copy(path + "/dir1/file1-1.txt", path + "/dir1/file1-1-copy.txt");
		assertTrue(new File(path + "/dir1/file1-1-copy.txt").exists());
	}
	
	@Test
	public void testMove() throws Exception {
		FileManager.move(path + "/dir2/file2-1.txt", path + "/dir2/file2-1-move.txt");
		assertTrue(new File(path + "/dir2/file2-1-move.txt").exists());
	}
	
	private void removeAll(String path) {
		if (new File(path).isDirectory()) {
			for (File element : new File(path).listFiles()) {
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