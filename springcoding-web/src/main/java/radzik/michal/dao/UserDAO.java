package radzik.michal.dao;

import java.util.List;

import radzik.michal.dto.UserDTO;
import radzik.michal.model.User;


public interface UserDAO {
	
	public User addUser(User user);

	public List<User> getAll();

	public boolean loginExecute(String login, String password);

	public User updateUser(UserDTO user);

	public void deleteUser(User user);

	User get(Long userId);
}
