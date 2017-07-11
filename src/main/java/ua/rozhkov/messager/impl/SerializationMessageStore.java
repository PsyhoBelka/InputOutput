package ua.rozhkov.messager.impl;

import ua.rozhkov.messager.AppendableObjectOutputStream;
import ua.rozhkov.messager.MessageStore;
import ua.rozhkov.messager.entity.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SerializationMessageStore implements MessageStore, Serializable {
	
	private String messagesStoreFile;
	
	public void setMessagesStoreFile(String messagesStoreFile) {
		this.messagesStoreFile = messagesStoreFile;
	}
	
	
	@Override
	public void persist(Message message) throws IOException, ClassNotFoundException {
		
		if (new File(messagesStoreFile).exists()) {
			FileOutputStream fileOutputStream = new FileOutputStream(messagesStoreFile, true);
			AppendableObjectOutputStream appendableObjectOutputStream = new AppendableObjectOutputStream(fileOutputStream);
			
			appendableObjectOutputStream.writeObject(message);
			appendableObjectOutputStream.close();
		} else {
			FileOutputStream fileOutputStream = new FileOutputStream(messagesStoreFile, true);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			
			objectOutputStream.writeObject(message);
			objectOutputStream.close();
		}
		
	}
	
	
	@Override
	public List <Message> read() throws IOException, ClassNotFoundException {
		
		List <Message> messagesList = new ArrayList <>();
		
		if (new File(messagesStoreFile).exists()) {
			FileInputStream fileInputStream = new FileInputStream(messagesStoreFile);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			
			while (fileInputStream.available() > 0) {
				messagesList.add((Message) objectInputStream.readObject());
			}
			
			objectInputStream.close();
		}
		return messagesList;
	}
	
	@Override
	public void persist(Collection <Message> list) throws IOException, ClassNotFoundException {
		for (Message message : list) {
			persist(message);
		}
	}
}
