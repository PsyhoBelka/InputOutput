package messager.impl;

import messager.MessageStore;
import messager.entity.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SerializationMessageStore implements MessageStore, Serializable {
	
	private String messageStoreFile;
	private List <Message> messages = new ArrayList <>();
	
	public void setMessageStoreFile(String messageStoreFile) {
		this.messageStoreFile = messageStoreFile;
	}
	
	
	@Override
	public void persist(Message message) throws IOException, ClassNotFoundException {
		List <Message> messagesList;
		messagesList = read();
		messagesList.add(message);
		
		FileOutputStream fileOutputStream = new FileOutputStream(messageStoreFile);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		
		objectOutputStream.writeObject(messagesList);
		objectOutputStream.flush();
		objectOutputStream.close();
	}
	
	@Override
	public List <Message> read() throws IOException, ClassNotFoundException {
		List <Message> messagesList;
		if (new File(messageStoreFile).exists()) {
			FileInputStream fileInputStream = new FileInputStream(messageStoreFile);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			
			messagesList = (List <Message>) objectInputStream.readObject();
			objectInputStream.close();
		} else {
			messagesList = new ArrayList <>();
		}
		return messagesList;
	}
	
	@Override
	public void persist(Collection <Message> list) throws IOException, ClassNotFoundException {
		List <Message> messagesList;
		messagesList = read();
		messagesList.addAll(list);
		
		FileOutputStream fileOutputStream = new FileOutputStream(messageStoreFile);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(messagesList);
		objectOutputStream.flush();
		objectOutputStream.close();
	}
}