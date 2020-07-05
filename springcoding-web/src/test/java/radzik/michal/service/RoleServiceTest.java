package radzik.michal.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import radzik.michal.dao.RoleDAO;
import radzik.michal.model.Role;
import radzik.michal.model.User;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
	
	@InjectMocks
	RoleServiceImpl roleService;
	
	@Mock
	private RoleDAO roleDAO;
	
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturnRoleWhenCreateRole() {
	
		//given
		Role role = new Role((long)1,"",new User());
	    Mockito.when(roleDAO.createRole(role)).thenReturn(role);
	    ArgumentCaptor<Role> argumentCaptor = ArgumentCaptor.forClass(Role.class);
	    
	    //when
	    Role roleCreated = roleService.createRole(role);
	    verify(roleDAO).createRole(argumentCaptor.capture());
	    
	    //then
	    assertThat(roleCreated, is(role));
	}
	
	@Test
	public void ShouldthrowNullWhenRoleIsNullWhenCreateRole() {
	
		//given
		Role role = null;
	    Mockito.when(roleDAO.createRole(role)).thenReturn(role);
	
	    //when
	    Role roleCreated = roleService.createRole(role);
	    
	    //then
	    assertThat(roleCreated, is(nullValue()));
	}
}
