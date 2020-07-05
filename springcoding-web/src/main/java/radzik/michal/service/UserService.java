package radzik.michal.service;

import java.util.List;

import radzik.michal.dto.UserDTO;
import radzik.michal.model.User;

public interface UserService {
	
	public User addUser(User user);

	public List<User> getAll();

	public boolean loginExecute(String login, String password);

	public User updateUser(UserDTO user);

	public void deleteUser(Long userId);
}
