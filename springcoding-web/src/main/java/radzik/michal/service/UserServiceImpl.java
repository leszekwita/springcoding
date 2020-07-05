package radzik.michal.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import radzik.michal.dao.UserDAO;
import radzik.michal.dto.UserDTO;
import radzik.michal.model.Role;
import radzik.michal.model.User;

@Service
public class UserServiceImpl implements UserService {

	public static final String FORMAT_DATY = "yyyy-MM/dd";

	Date date;

	private UserDAO userDAO;

	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public User addUser(User user) {

		List<String> rolesTexts = user.getRolesTexts();
		setRolesInUser(user, rolesTexts);
		return userDAO.addUser(user);
	}

	@Override
	@Transactional
	public List<User> getAll() {

		List<User> users = userDAO.getAll();
		List<User> userDtos = new ArrayList<User>();
		convertUsersToUsersDTOs(users, userDtos);
		return userDtos;
	}

	@Override
	@Transactional
	public User updateUser(UserDTO user) {

		setBirthDateInUser(user);
		List<String> rolesTexts = user.getRolesTexts();
		setRolesInUser(user, rolesTexts);
		return userDAO.updateUser(user);
	}

	@Override
	@Transactional
	public void deleteUser(Long userId) {
		User user = this.userDAO.get(userId);
		this.userDAO.deleteUser(user);
	}
	
	public boolean loginExecute(String login, String password) {
		return userDAO.loginExecute(login, password);

	}

	private void setRolesInUser(User user, List<String> rolesTexts) {
		for (String roleText : rolesTexts) {
			Role role = new Role();
			role.setName(roleText);
			user.getRoles().add(role);
			role.setUser(user);
		}
	}

	private void convertUsersToUsersDTOs(List<User> users, List<User> userDtos) {
		for (User user : users) {
			User userDto = new UserDTO(user);
			userDtos.add(userDto);
		}
	}

	private void setBirthDateInUser(UserDTO user) {
		String dateText = user.getBirthDateText();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = dateFormat.parse(dateText);
			user.setBirthDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}