package messager.impl;

import messager.entity.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CustomMessageStoreTest {
	
	private CustomMessageStore customMessageStore=new CustomMessageStore();
	private String messagesStoreFile = "src\\test\\resources\\messager\\custMess.dat";
	
	@Before
	public void before(){
		customMessageStore.setMessagesStoreFile(messagesStoreFile);
		new File("src\\test\\resources\\messager").mkdirs();
		if (new File(messagesStoreFile).exists()){
			new File(messagesStoreFile).delete();
		}
	}
	
	@After
	public void after(){
		new File(messagesStoreFile).delete();
		new File("src\\test\\resources\\messager").delete();
	}
	
	@Test
	public void testPersist() throws Exception {
		customMessageStore.persist(new Message(12, LocalDate.now(),"first custom message"));
		assertEquals(12,customMessageStore.read().get(0).getId());
	}
	
	@Test
	public void testRead() throws Exception {
		customMessageStore.persist(new Message(12, LocalDate.now(),"first custom message"));
		assertEquals(12, customMessageStore.read().get(0).getId());
	}
	
	@Test
	public void testPersist1() throws Exception {
		List<Message> messages=new ArrayList <>();
		messages.add(new Message(1, LocalDate.now(), "first custom message"));
		messages.add(new Message(2, LocalDate.now(), "second custom message"));
		messages.add(new Message(3, LocalDate.now(), "third custom message"));
		
		customMessageStore.persist(messages);
		assertEquals(3, customMessageStore.read().get(2).getId());
	}
	
}