package radzik.michal.service;

import javax.mail.internet.AddressException;

import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

public interface SchedulerService {

	void onTime() throws AddressException, MessagingException, javax.mail.MessagingException;
}
