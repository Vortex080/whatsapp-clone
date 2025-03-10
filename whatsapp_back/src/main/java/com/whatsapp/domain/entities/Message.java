package com.whatsapp.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 */
@Entity
@Table(name = "Message")
public class Message {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The author. */
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private User author;

	/** The text. */
	private String text;

	/** The date. */
	private LocalDateTime date;

	/** The chat. */
	private Long destino;

	/**
	 * Instantiates a new message.
	 */
	public Message() {
		super();
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param author the author
	 * @param text   the text
	 * @param date   the date
	 */
	public Message(User author, String text, LocalDateTime date) {
		super();
		this.author = author;
		this.text = text;
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param id     the id
	 * @param author the author
	 * @param text   the text
	 * @param date   the date
	 */
	public Message(Long id, User author, String text, LocalDateTime date) {
		super();
		this.id = id;
		this.author = author;
		this.text = text;
		this.date = date;
	}

	/**
	 * Gets the destino.
	 *
	 * @return the destino
	 */
	public Long getDestino() {
		return destino;
	}

	/**
	 * Sets the destino.
	 *
	 * @param destino the new destino
	 */
	public void setDestino(Long destino) {
		this.destino = destino;
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
	 * Gets the author.
	 *
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 *
	 * @param author the new author
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
