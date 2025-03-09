package com.whatsapp.infrastructure.repositories;

import java.util.List;

import com.whatsapp.domain.entities.Message;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped // 💡 Indica que es un bean CDI accesible globalmente
@Transactional // 💡 Permite que los métodos sean transaccionales
public class MessageDAO {

	/** The em. */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Save.
	 *
	 * @param message the message
	 */
	public void save(Message message) {
		em.persist(message);
	}

	/**
	 * Find.
	 *
	 * @param id the id
	 * @return the message
	 */
	public Message find(Long id) {
		return em.find(Message.class, id);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Message> findAll() {
		return em.createQuery("SELECT u FROM message u", Message.class).getResultList();
	}

	/**
	 * Update.
	 *
	 * @param message the message
	 */
	public void update(Message message) {
		em.merge(message);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id) {
		Message message = find(id);
		if (message != null) {
			em.remove(message);
		}
	}
}
