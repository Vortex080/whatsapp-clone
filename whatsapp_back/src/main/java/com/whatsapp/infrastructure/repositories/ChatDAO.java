package com.whatsapp.infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;

import com.whatsapp.domain.entities.Chat;
import com.whatsapp.domain.entities.Message;
import com.whatsapp.domain.entities.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatDAO.
 */
@ApplicationScoped // 💡 Indica que es un bean CDI accesible globalmente
@Transactional // 💡 Permite que los métodos sean transaccionales
public class ChatDAO {

	/** The em. */
	@PersistenceContext
	private EntityManager em;

	
	
	/**
	 * Instantiates a new chat DAO.
	 */
	public ChatDAO() {
		super();
	}

	/**
	 * Save.
	 *
	 * @param chat the chat
	 */
	public void save(Chat chat) {
		// Asegurar que los usuarios existen antes de asociarlos
		for (int i = 0; i < chat.getUsers().size(); i++) {
			User managedUser = em.find(User.class, chat.getUsers().get(i).getId());
			if (managedUser != null) {
				chat.getUsers().set(i, managedUser);
			} else {
				throw new RuntimeException("Usuario con ID " + chat.getUsers().get(i).getId() + " no existe");
			}
		}

		// Asegurar que los mensajes tienen su autor y chat correctamente asignados
		for (Message message : chat.getMessages()) {
			User author = em.find(User.class, message.getAuthor().getId());
			if (author != null) {
				message.setAuthor(author);
			} else {
				throw new RuntimeException("Autor con ID " + message.getAuthor().getId() + " no existe");
			}
			message.setChat(chat); // Relacionar el mensaje con el chat
		}

		em.persist(chat);
	}

	/**
	 * Find.
	 *
	 * @param id the id
	 * @return the chat
	 */
	public Chat find(Long id) {
		return em.find(Chat.class, id);
	}
	
	
	public List<Chat> findChatIdsByUserId(Long userId) {
	    TypedQuery<Long> query = em.createQuery(
	        "SELECT c.id FROM Chat c JOIN c.users u WHERE u.id = :userId", Long.class);
	    query.setParameter("userId", userId);
	    
	    List<Long> chats = query.getResultList();
	    List<Chat> chatsA = new ArrayList<>();
	    for (Long chat : chats) {
	        Chat returnChat = em.find(Chat.class, chat);
	        chatsA.add(returnChat);
	    }
	    
	    return chatsA;
	}


	
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Chat> findAll() {
		return em.createQuery("SELECT u FROM chat u", Chat.class).getResultList();
	}

	/**
	 * Update.
	 *
	 * @param chat the chat
	 */
	public void update(Chat chat) {
		em.merge(chat);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id) {
		Chat chat = find(id);
		if (chat != null) {
			em.remove(chat);
		}
	}
}
