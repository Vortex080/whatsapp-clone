package com.whatsapp.domain.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Chat")
public class Chat {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The users. */
	@ManyToMany
	@JoinTable(name = "chat_users", joinColumns = @JoinColumn(name = "chat_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;

	/** The messages. */
	@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Message> messages;

	/**
	 * Instantiates a new chat.
	 */
	public Chat() {
		super();
	}

	/**
	 * Instantiates a new chat.
	 *
	 * @param users    the users
	 * @param messages the messages
	 */
	public Chat(List<User> users, List<Message> messages) {
		super();
		this.users = users;
		this.messages = messages;
	}

	/**
	 * Instantiates a new chat.
	 *
	 * @param id       the id
	 * @param users    the users
	 * @param messages the messages
	 */
	public Chat(Long id, List<User> users, List<Message> messages) {
		super();
		this.id = id;
		this.users = users;
		this.messages = messages;
	}

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 */
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void addMessage(Message msg) {
		this.messages.add(msg);
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * Sets the messages.
	 *
	 * @param messages the new messages
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
