package messager.impl;

import messager.MessageStore;
import messager.entity.Message;

import java.util.Collection;
import java.util.List;

public class CustomMessageStore implements MessageStore {
	
	@Override
	public void persist(Message message) {
		
	}
	
	@Override
	public List <Message> read() {
		return null;
	}
	
	@Override
	public void persist(Collection<Message> list) {
		
	}
}
