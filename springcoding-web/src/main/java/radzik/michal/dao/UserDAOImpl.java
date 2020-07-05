package radzik.michal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import radzik.michal.dto.UserDTO;
import radzik.michal.model.Role;
import radzik.michal.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext(unitName = "michal")
	private EntityManager em;

	
	@Override
	@Transactional
	public User addUser(User user) {
		em.persist(user);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getAll() {
		Query query = em.createQuery("FROM User user");
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	@Transactional
	public boolean loginExecute(String login, String password) {
		Query query = em.createQuery("FROM User user where user.email =:email and user.password =:password");
		query.setParameter("email", login);
		query.setParameter("password", password);
		List<User> users = query.getResultList();

		if (users.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	@Transactional
	public User updateUser(UserDTO user) {
		User userOld = em.find(User.class, user.getId());
		List<Role> rolesOld = userOld.getRoles();

		for (Role role : rolesOld) {
			em.remove(role);
		}
		em.merge(user.getUser());
		return user;
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		em.remove(user);

	}

	@Override
	public User get(Long userId) {
		return em.find(User.class, userId);
	}
}
