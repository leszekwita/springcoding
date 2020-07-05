package radzik.michal;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import radzik.michal.model.UserType;

public class EnumTest {

	private static final Logger log = LoggerFactory.getLogger(EnumTest.class);
	
	public static void main(String[] args) {

		List<UserType> userTypesValues = Arrays.asList(UserType.values());
		for (UserType userType : userTypesValues) {
			log.info(userType.name());
		}
	}

}
