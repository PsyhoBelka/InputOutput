package ua.rozhkov.loganalyzer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogAnalyzer {
	private String logFile;
	
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}
	
	public Collection <LogToken> analyze(String path, LocalDateTime timeFrom, LocalDateTime timeTo) throws IOException {
		setLogFile(path);
		List <String> stringsList = read();
		List <LogToken> listLogTokens = new ArrayList <>();
		
		for (String s : stringsList) {
			LogToken tempLogToken = new LogToken();
			tempLogToken.setTime(regexDateTime(s));
			tempLogToken.setMethod(HttpMethod.valueOf(regexMethod(s)));
			tempLogToken.setMessage(regexMessage(s));
			listLogTokens.add(tempLogToken);
		}
		
		List <LogToken> matchedLogTokens = new ArrayList <>();
		for (LogToken logToken : listLogTokens) {
			if ((logToken.getTime().compareTo(timeFrom) > 0) && (logToken.getTime().compareTo(timeTo) < 0)) {
				matchedLogTokens.add(logToken);
			}
		}
		
		return matchedLogTokens;
	}
	
	public List <String> read() throws IOException {
		List <String> stringsList = new ArrayList <>();
		String[] strings;
		
		if (new File(logFile).exists()) {
			FileReader fileReader = new FileReader(logFile);
			String s = "";
			while (fileReader.ready()) {
				s += (char) fileReader.read();
			}
			strings = s.split("\r\n");
			for (String string : strings) {
				stringsList.add(string);
			}
			fileReader.close();
		}
		
		return stringsList;
	}
	
	public LocalDateTime regexDateTime(String text) {
		String regExp = "(\\d{2}\\/[a-zA-Z]{3}\\/\\d{4}:\\d{2}:\\d{2}:\\d{2})";
		String dateTime = regExpGo(regExp, text);
		LocalDate localDate = LocalDate.parse(dateTime.substring(0, 11), DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale.ENGLISH));
		LocalTime localTime = LocalTime.parse(dateTime.substring(12));
		return LocalDateTime.of(localDate, localTime);
	}
	
	private String regexMethod(String text) {
		String regExp = "(GET)|(POST)";
		return regExpGo(regExp, text);
	}
	
	private String regexMessage(String text) {
		String regExp = "(\".*?\")";
		return regExpGo(regExp, text);
	}
	
	private String regExpGo(String regExp, String text) {
		String result = "";
		Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(text);
		if (m.find()) {
			result = m.group(1);
		}
		return result;
	}
}
