package com.whatsapp.infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;

import com.whatsapp.domain.entities.Message;
import com.whatsapp.domain.entities.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
		em.flush();
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

	public List<Message> findByUsers(Long id1, Long id2) {
		TypedQuery<Message> query = em.createQuery(
			    "SELECT m FROM Message m WHERE (m.author.id = :id1 AND m.destino = :id2) OR (m.author.id = :id2 AND m.destino = :id1)",
			    Message.class);

			query.setParameter("id1", id1);
			query.setParameter("id2", id2);



		List<Message> results = query.getResultList();
		return results.isEmpty() ? null : results;
	}
}
