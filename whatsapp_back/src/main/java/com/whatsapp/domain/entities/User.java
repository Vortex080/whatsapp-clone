package com.whatsapp.domain.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The name. */
	@Column(unique = true, nullable = false)
	private String name;

	/** The email. */
	@Column(unique = true, nullable = false)
	private String email;

	/** The pass. */
	@Column(nullable = false)
	@JsonIgnore
	private String pass;

	/** The photo. */
	@Column(nullable = false)
	private String photo;

	/** The chats. */
	@ManyToMany(mappedBy = "users")
	@JsonIgnore
	private List<Chat> chats;

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param id    the id
	 * @param name  the name
	 * @param email the email
	 * @param pass  the pass
	 * @param photo the photo
	 */
	public User(Long id, String name, String email, String pass, String photo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.photo = photo;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param name  the name
	 * @param email the email
	 * @param pass  the pass
	 * @param photo the photo
	 */
	public User(String name, String email, String pass, String photo) {
		super();
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.photo = photo;
	}

	/**
	 * Gets the chats.
	 *
	 * @return the chats
	 */
	public List<Chat> getChats() {
		return chats;
	}

	public void setChats(List<Chat> chats) {
		this.chats = chats;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the pass.
	 *
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Sets the pass.
	 *
	 * @param pass the new pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * Gets the photo.
	 *
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Sets the photo.
	 *
	 * @param photo the new photo
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
