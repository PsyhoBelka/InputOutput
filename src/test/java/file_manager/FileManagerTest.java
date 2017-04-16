package file_manager;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runners.model.TestClass;
import org.mockito.Mockito;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileManagerTest {
	//FileManager fileManager=new FileManager();
	
	@Test
	public void testCalculateFiles() throws Exception {
		String path= TestClass.class.getResource("/file_manager/dirs").toString();
		path=path.substring(6);
		assertEquals(7,FileManager.calculateFiles(path));
	}
	
	@Test
	public void testCalculateDirs() throws Exception {
		String path= TestClass.class.getResource("/file_manager/dirs").toString();
		path=path.substring(6);
		assertEquals(6,FileManager.calculateDirs(path));
	}
	
	@Test
	public void testCopy() throws Exception {
		String path= TestClass.class.getResource("/file_manager/dirs").toString();
		path=path.substring(6);
		FileManager.copy(path+"/1/text1-1.txt",path+"/1/text1-2.txt");
		assertTrue(new File(path+"/1/text1-2.txt").exists());
	}
	
	@Test
	public void testMove() throws Exception {
		String path= TestClass.class.getResource("/file_manager/dirs").toString();
		path=path.substring(6);
		FileManager.move(path+"/3/text3-1.txt",path+"/3/text3-5.txt");
		assertTrue(new File(path+"/3/text3-5.txt").exists());
	}
	
}