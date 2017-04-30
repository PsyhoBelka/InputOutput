package messager.impl;

import messager.MessageStore;
import messager.entity.Message;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomMessageStore implements MessageStore {
	
	private String messagesStoreFile;
	
	public void setMessagesStoreFile(String messagesStoreFile) {
		this.messagesStoreFile = messagesStoreFile;
	}
	
	@Override
	public void persist(Message message) throws IOException {
		FileOutputStream fileOutputStream;
		if (new File(messagesStoreFile).exists()) {
			fileOutputStream = new FileOutputStream(messagesStoreFile, true);
			
		} else {
			fileOutputStream = new FileOutputStream(messagesStoreFile);
		}
		DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
		
		dataOutputStream.writeInt(message.getId());
		dataOutputStream.writeUTF(message.getDate().toString());
		dataOutputStream.writeUTF(message.getContent());
		
		dataOutputStream.flush();
		dataOutputStream.close();
	}
	
	@Override
	public List <Message> read() throws IOException {
		List <Message> messagesList = new ArrayList <>();
		
		if (new File(messagesStoreFile).exists()) {
			FileInputStream fileInputStream = new FileInputStream(messagesStoreFile);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			
			while (dataInputStream.available() > 0) {
				Message tempMessage = new Message();
				tempMessage.setId(dataInputStream.readInt());
				tempMessage.setDate(LocalDate.parse(dataInputStream.readUTF().toString()));
				tempMessage.setContent(dataInputStream.readUTF().toString());
				messagesList.add(tempMessage);
			}
			
			dataInputStream.close();
		}
		
		return messagesList;
	}
	
	@Override
	public void persist(Collection <Message> list) throws IOException {
		FileOutputStream fileOutputStream;
		if (new File(messagesStoreFile).exists()) {
			fileOutputStream=new FileOutputStream(messagesStoreFile,true);
		}else{
			fileOutputStream=new FileOutputStream(messagesStoreFile);
		}
		DataOutputStream dataOutputStream=new DataOutputStream(fileOutputStream);
		
		for (Message message: list) {
			dataOutputStream.writeInt(message.getId());
			dataOutputStream.writeUTF(message.getDate().toString());
			dataOutputStream.writeUTF(message.getContent());
		}
		
		dataOutputStream.flush();
		dataOutputStream.close();
	}
}
