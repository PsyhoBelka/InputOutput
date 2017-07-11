package ua.rozhkov.messager.impl;

import ua.rozhkov.messager.entity.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SerializationMessageStoreTest {
	
	private SerializationMessageStore serializationMessageStore = new SerializationMessageStore();
	private String messagesStoreFile = "src\\test\\resources\\ua.rozhkov.messager\\serMess.dat";
	
	@Before
	public void setUp() throws Exception {
		serializationMessageStore.setMessagesStoreFile(messagesStoreFile);
		new File("src\\test\\resources\\ua.rozhkov.messager").mkdirs();
		
		
		Message message0 = new Message(0, LocalDate.now(), "setup message");
		serializationMessageStore.persist(message0);
	}
	
	@After
	public void after() {
		new File(messagesStoreFile).delete();
		new File("src\\test\\resources\\ua.rozhkov.messager").delete();
	}
	
	@Test
	public void testPersist() throws Exception {
		Message message1 = new Message(1, LocalDate.now(), "first message");
		serializationMessageStore.persist(message1);
		
		Message message2 = new Message(2, LocalDate.now(), "second message");
		serializationMessageStore.persist(message2);
		
		Message message3 = new Message(3, LocalDate.now(), "third message");
		serializationMessageStore.persist(message3);
		
		List <Message> messageList = serializationMessageStore.read();
		Assert.assertEquals(3, messageList.get(3).getId());
	}
	
	@Test
	public void testRead() throws Exception {
		List <Message> messageList = serializationMessageStore.read();
		Assert.assertEquals(0, messageList.get(0).getId());
	}
	
	@Test
	public void testPersistCollection() throws Exception {
		List <Message> messages = new ArrayList <>();
		messages.add(new Message(1, LocalDate.now(), "first list message"));
		messages.add(new Message(2, LocalDate.now(), "second list message"));
		messages.add(new Message(3, LocalDate.now(), "third list message"));
		serializationMessageStore.persist(messages);
		messages = serializationMessageStore.read();
		Assert.assertEquals(1, messages.get(1).getId());
	}
	
}