package radzik.michal.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import radzik.michal.dao.UserDAO;
import radzik.michal.dto.UserDTO;
import radzik.michal.model.Role;
import radzik.michal.model.User;
import radzik.michal.model.UserType;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserDAO userDAO;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllUsers() {

		// given
		List<User> users = getUsers();
		Mockito.when(userDAO.getAll()).thenReturn(users);

		// when
		List<User> userActual = userService.getAll();

		// then
		verify(userDAO, times(1)).getAll();
		assertThat(userActual, hasSize(4));
	}

	@Test
	public void shouldReturnUserWhenAddUser() {

		// given
		User user = new User();
		Mockito.when(userDAO.addUser(user)).thenReturn(user);

		// when
		User userActual = userService.addUser(user);

		// then
		verify(userDAO, times(1)).addUser(user);
		assertThat(userActual, is(user));

	}

	@Test
	public void shouldReturnUserWhenUpdate() throws Exception {

		// given
		UserDTO user = new UserDTO();
		user.setBirthDateText("1980-05-11");
		Mockito.when(userDAO.updateUser(user)).thenReturn(user);
		ArgumentCaptor<UserDTO> captor = ArgumentCaptor.forClass(UserDTO.class);

		// when
		User userUpdated = userService.updateUser(user);

		// then
		verify(userDAO).updateUser(captor.capture());
		assertThat(user, is(userUpdated));
	}

	@Test(expected = NullPointerException.class)
	public void ShouldThrowNullWhenUsersIsNullWhenGetAll() {

		// given
		List<User> users = null;
		Mockito.when(userDAO.getAll()).thenReturn(users);

		// when
		userService.getAll();

	}

	@Test
	public void deleteUser() {

		// given
		User user = new User();
		doNothing().when(userDAO).deleteUser(user);
		Mockito.when(userDAO.get(user.getId())).thenReturn(user);
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		
		// when
		userService.deleteUser(user.getId());

		// then
		verify(userDAO).deleteUser(captor.capture());
		Mockito.verify(userDAO, times(1)).deleteUser(user);
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenDeleteUserIsNull() {

		// given
		User user = null;
		Mockito.when(userDAO.get(user.getId())).thenReturn(user);

		// when
		userService.deleteUser(user.getId());
	}

	@Test
	public void shouldBeTrueWhenloginAndPasswordAreCorrect() {

		// given
		String login = "mradzik";
		String password = "mradzik";
		ArgumentCaptor<String> loginArgumentCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> passwordArgumentCaptor = ArgumentCaptor.forClass(String.class);

		// when
		Mockito.when(userDAO.loginExecute(login, password)).thenReturn(true);
		boolean result = userService.loginExecute(login, password);

		// then
		verify(userDAO).loginExecute(loginArgumentCaptor.capture(), passwordArgumentCaptor.capture());
		assertThat(result, is(true));

	}

	@Test
	public void shouldBeFalseWhenloginAndPasswordAreNull() {

		// given
		String login = null;
		String password = null;

		// when
		boolean result = userService.loginExecute(login, password);

		// then
		Mockito.verify(userDAO, times(1)).loginExecute(login, password);
		assertThat(result, is(false));
	}

	private List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		User user1 = new User((long) 1, "Tomasz", "Janowicki", "janowiki@wp.pl", "1234", new Date(), true, null,
				UserType.Admin, null, null);
		User user2 = new User((long) 2, "Mateusz", "Kowalski", "tyt@wp.pl", "5678", new Date(), true, "Woman",
				UserType.Premium, new ArrayList<Role>(), new ArrayList<String>());
		User user3 = new User((long) 3, "Mateusz", "Kowalski", "pol@op.pl", "9876", new Date(), true, "Woman",
				UserType.User, new ArrayList<Role>(), new ArrayList<String>());
		User user4 = new User((long) 4, "Jerzy", "Gawlina", "jerzy@op.pl", "4323", new Date(), true, "Man",
				UserType.User, new ArrayList<Role>(), new ArrayList<String>());
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		return users;
	}
}
