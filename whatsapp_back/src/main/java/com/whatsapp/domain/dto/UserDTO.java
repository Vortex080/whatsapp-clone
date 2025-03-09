package com.whatsapp.domain.dto;

public class UserDTO {

	/** The name. */
	private String name;

	/** The email. */
	private String email;

	/** The pass. */
	private String pass;

	/** The photo. */
	private String photo;

	/**
	 * Instantiates a new user DTO.
	 */
	public UserDTO() {
		super();
	}

	/**
	 * Instantiates a new user DTO.
	 *
	 * @param name  the name
	 * @param email the email
	 * @param pass  the pass
	 * @param photo the photo
	 */
	public UserDTO(String name, String email, String pass, String photo) {
		super();
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.photo = photo;
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
