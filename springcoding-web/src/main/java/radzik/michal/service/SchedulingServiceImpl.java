package radzik.michal.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

import radzik.michal.util.Emailer;

@Service("scheduler")
public class SchedulingServiceImpl implements SchedulerService {
	
	private Emailer emailer;
	
	public SchedulingServiceImpl(Emailer emailer) 
	{ this.emailer = emailer; }
	 
	
	@Scheduled(cron = "* * */8 * * ?")
	public void onTime() throws AddressException, MessagingException, javax.mail.MessagingException {
		List<String> emails = new ArrayList<String>();
		emails.add("leszekwita@o2.pl");
		emailer.sendEmail(emails, "Mail testowy", "kdfjkhsdkhfkjsdhdkfh");
	}
}
