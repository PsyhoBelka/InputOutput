package ua.rozhkov.messager;

import ua.rozhkov.messager.entity.Message;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface MessageStore {
	
	void persist(Message message) throws IOException, ParseException, ClassNotFoundException;
	
	List<Message> read() throws IOException, ParseException, ClassNotFoundException;
	
	void persist(Collection<Message> list) throws IOException, ClassNotFoundException;
}
