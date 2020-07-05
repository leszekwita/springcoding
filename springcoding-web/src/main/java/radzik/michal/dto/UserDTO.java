package radzik.michal.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import radzik.michal.model.Role;
import radzik.michal.model.User;

public class UserDTO extends User {

	private User user = new User();

	private String birthDateText;

	private String checkedRadMan;

	private String checkedRadWom;

	public UserDTO() {
		super();
	}

	public UserDTO(User user) {
		this.user = user;
		this.setId(user.getId());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setBirthDate(user.getBirthDate());
		this.setSex(user.getSex());
		this.setUserType(user.getUserType());

		Date birthDate = user.getBirthDate();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		if (birthDate != null) {
			String birthDateText = sdf.format(birthDate);
			this.setBirthDateText(birthDateText);
		} else {
			this.setBirthDateText("");
		}

		String sex = user.getSex();
		if (sex != null) {
			if (sex.trim().equals("Man")) {
				this.setCheckedRadMan("checked");
				this.setCheckedRadWom("");

			} else if (sex.trim().equals("Woman")) {
				this.setCheckedRadWom("checked");
				this.setCheckedRadMan("");

			}
		}
		List<Role> roles = user.getRoles();
		List<Role> rolesDtos = new ArrayList<Role>();

		Role emptyRole1 = new RoleDTO(1);
		Role emptyRole2 = new RoleDTO(2);
		Role emptyRole3 = new RoleDTO(3);

		rolesDtos.add(emptyRole1);
		rolesDtos.add(emptyRole2);
		rolesDtos.add(emptyRole3);

		if (roles != null) {
			for (Role role : roles) {
				RoleDTO roleDTO = new RoleDTO(role);
				rolesDtos.set(roleDTO.getRoleNumber() - 1, roleDTO);
			}
		}
		setRoles(rolesDtos);
	}

	public User getUser() {
		user.setId(getId());
		user.setActive(getActive());
		user.setBirthDate(getBirthDate());
		user.setEmail(getEmail());
		user.setFirstName(getFirstName());
		user.setLastName(getLastName());
		user.setPassword(getPassword());
		user.setRoles(getRoles());
		user.setRolesTexts(getRolesTexts());
		user.setSex(getSex());
		user.setUserType(getUserType());
		return user;
	}

	public String getCheckedRadMan() {
		return checkedRadMan;
	}

	public void setCheckedRadMan(String checkedRadMan) {
		this.checkedRadMan = checkedRadMan;
	}

	public String getCheckedRadWom() {
		return checkedRadWom;
	}

	public void setCheckedRadWom(String checkedRadWom) {
		this.checkedRadWom = checkedRadWom;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBirthDateText() {
		return birthDateText;
	}

	public void setBirthDateText(String birthDateText) {
		this.birthDateText = birthDateText;
	}
}
