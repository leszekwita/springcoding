package radzik.michal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import radzik.michal.dao.RoleDAO;
import radzik.michal.model.Role;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleDAO roleDAO;
	
	public RoleServiceImpl(RoleDAO roleDAO) {
		 this.roleDAO = roleDAO; }
	
	
	@Override
	@Transactional
	public Role createRole(Role role) {
		return roleDAO.createRole(role);
	}

}
