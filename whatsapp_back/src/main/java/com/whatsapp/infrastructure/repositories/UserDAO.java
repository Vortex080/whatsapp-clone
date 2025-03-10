package com.whatsapp.infrastructure.repositories;

import java.util.List;

import com.whatsapp.domain.entities.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAO.
 */
@ApplicationScoped // 💡 Indica que es un bean CDI accesible globalmente
@Transactional // 💡 Permite que los métodos sean transaccionales
public class UserDAO {

	/** The em. */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Save.
	 *
	 * @param user the user
	 */
	public void save(User user) {
		em.persist(user);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the user
	 */
	public User findById(Long id) {
		return em.find(User.class, id);
	}

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	public User findByEmail(String email) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
		query.setParameter("email", email);
		List<User> results = query.getResultList();
		return results.isEmpty() ? null : results.get(0);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<User> findAll() {
		return em.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	/**
	 * Update.
	 *
	 * @param user the user
	 */
	public void update(User user) {
		em.merge(user);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id) {
		User user = findById(id);
		if (user != null) {
			em.remove(user);
		}
	}

	/**
	 * Find by user.
	 *
	 * @param name the name
	 * @return the user
	 */
	public User findByUser(String name) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class);
		query.setParameter("name", name);
		List<User> results = query.getResultList();
		return results.isEmpty() ? null : results.get(0);
	}
}
