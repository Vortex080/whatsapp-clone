package com.whatsapp.domain.dto;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatDTO.
 */
public class ChatDTO {

	/** The users. */
	private List<Long> users;

	/** The messages. */
	private List<MessageDTO> messages;

	/**
	 * Instantiates a new chat DTO.
	 */
	public ChatDTO() {
		super();
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<Long> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<Long> users) {
		this.users = users;
	}

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public List<MessageDTO> getMessages() {
		return messages;
	}

	/**
	 * Sets the messages.
	 *
	 * @param messages the new messages
	 */
	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}

}
