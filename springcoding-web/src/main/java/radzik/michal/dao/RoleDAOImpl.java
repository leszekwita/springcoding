package radzik.michal.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import radzik.michal.model.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
	
	@PersistenceContext(unitName = "michal")
	private EntityManager em;
	
	@Override
	@Transactional
	public Role createRole(Role role) {
		em.persist(role);
		return role;
	}

}
