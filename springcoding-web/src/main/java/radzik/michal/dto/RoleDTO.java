package radzik.michal.dto;

import radzik.michal.model.Role;

public class RoleDTO extends Role {

	private Integer roleNumber;

	public RoleDTO(Integer roleNumber) {
		this.roleNumber = roleNumber;
	}

	public RoleDTO(Role role) {
		this.setId(role.getId());
		this.setName(role.getName());
		this.setUser(role.getUser());

		String roleName = getName();
		String[] roleNameArr = roleName.split(" ");
		this.setRoleNumber(Integer.valueOf(roleNameArr[1]));
	}

	public Integer getRoleNumber() {
		return roleNumber;
	}

	public void setRoleNumber(Integer roleNumber) {
		this.roleNumber = roleNumber;
	}
}
