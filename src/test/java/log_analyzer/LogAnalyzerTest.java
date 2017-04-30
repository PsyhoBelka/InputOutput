package log_analyzer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogAnalyzerTest {
	
	
	private String logFilePath = "src\\test\\resources\\log-analyzer\\log.txt";
	LogAnalyzer logAnalyzer = new LogAnalyzer();
	
	@Before
	public void before() throws Exception {
		
	}
	
	@After
	public void after() throws Exception {
		
	}
	
	
	@Test
	public void testAnalyze() throws Exception {
		System.out.println(
				logAnalyzer.analyze(logFilePath,
						LocalDateTime.parse("07/Mar/2004 16:05:49", DateTimeFormatter.ofPattern("d/MMM/yyyy HH:mm:ss").withLocale(Locale.ENGLISH)),
						LocalDateTime.parse("07/Mar/2004 16:54:55", DateTimeFormatter.ofPattern("d/MMM/yyyy HH:mm:ss").withLocale(Locale.ENGLISH)))
		);
		
	}
}