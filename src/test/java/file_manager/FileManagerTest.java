package file_manager;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileManagerTest {
	
	private String path = "src/test/resources/file_manager/dirs";
	
	@Test
	public void testCalculateFiles() throws Exception {
		assertEquals(7, FileManager.calculateFiles(path));
	}
	
	@Test
	public void testCalculateDirs() throws Exception {
		assertEquals(6, FileManager.calculateDirs(path));
	}
	
	@Test
	public void testCopy() throws Exception {
		FileManager.copy(path + "/1/text1-1.txt", path + "/1/text1-2.txt");
		assertTrue(new File(path + "/1/text1-2.txt").exists());
	}
	
	@Test
	public void testMove() throws Exception {
		FileManager.move(path + "/3/text3-1.txt", path + "/3/text3-5.txt");
		assertTrue(new File(path + "/3/text3-5.txt").exists());
	}
	
}