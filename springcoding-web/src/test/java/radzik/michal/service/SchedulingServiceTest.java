package radzik.michal.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.core.env.Environment;

import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import radzik.michal.util.Emailer;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Emailer.class})
public class SchedulingServiceTest {
	
	@InjectMocks
	SchedulingServiceImpl schedulingService;
	
	@Mock
	private Environment env;
	
	@Mock
	Emailer emailer;
	
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testOnTime() throws MessagingException, AddressException, javax.mail.MessagingException {

		// given
		List<String> emails = new ArrayList<String>();
		emails.add("leszekwita@o2.pl");
		doNothing().when(emailer).sendEmail(emails, "Mail testowy", "kdfjkhsdkhfkjsdhdkfh");

		// when
		schedulingService.onTime();

		// then
		Mockito.verify(emailer, times(1)).sendEmail(emails, "Mail testowy", "kdfjkhsdkhfkjsdhdkfh");
	}

}
