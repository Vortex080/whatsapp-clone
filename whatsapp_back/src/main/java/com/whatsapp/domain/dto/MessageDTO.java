package com.whatsapp.domain.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageDTO.
 */
public class MessageDTO {

	/** The author. */
	private Long idAuthor;

	/** The text. */
	private String text;

	/** The date. */
	private String date;

	/** The chat id. */
	private Long dest;

	/**
	 * Instantiates a new message DTO.
	 */
	public MessageDTO() {
		super();
	}

	/**
	 * Gets the dest.
	 *
	 * @return the dest
	 */
	public Long getDest() {
		return dest;
	}

	/**
	 * Sets the dest.
	 *
	 * @param dest the new dest
	 */
	public void setDest(Long dest) {
		this.dest = dest;
	}

	/**
	 * Gets the id author.
	 *
	 * @return the id author
	 */
	public Long getIdAuthor() {
		return idAuthor;
	}

	/**
	 * Sets the id author.
	 *
	 * @param idAuthor the new id author
	 */
	public void setIdAuthor(Long idAuthor) {
		this.idAuthor = idAuthor;
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
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

}
