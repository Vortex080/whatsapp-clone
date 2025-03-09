package com.whatsapp.infrastructure.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.whatsapp.domain.dto.MessageDTO;
import com.whatsapp.domain.entities.Message;
import com.whatsapp.infrastructure.repositories.ChatDAO;
import com.whatsapp.infrastructure.repositories.MessageDAO;
import com.whatsapp.infrastructure.repositories.UserDAO;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageRest {

	/** The message DAO. */
	@Inject
	private MessageDAO messageDAO;
	@Inject
	private UserDAO userDAO;
	@Inject
	private ChatDAO chatDAO;

	/**
	 * Creates the message.
	 *
	 * @param message the message
	 * @return the response
	 */
	@POST
	public Response createMessage(MessageDTO message) {
		if (message.getIdAuthor() == null || message.getText() == null || message.getDate() == null
				|| message.getChatId() == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("El autor, la fecha y el mensaje son obligatorios").build();
		}

		Message newMessage = new Message();

		newMessage.setText(message.getText());
		newMessage.setAuthor(userDAO.findById(message.getIdAuthor()));
		newMessage.setDate(LocalDateTime.now());
		newMessage.setChat(chatDAO.find(message.getChatId()));

		messageDAO.save(newMessage);
		return Response.status(Response.Status.CREATED).entity(message).build();
	}


	/**
	 * Gets the message.
	 *
	 * @param id the id
	 * @return the message
	 */
	@GET
	@Path("/{id}")
	public Response getMessage(@PathParam("id") Long id) {
		Message message = messageDAO.find(id);
		if (message == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		MessageDTO messageDTO = new MessageDTO();
		
		messageDTO.setChatId(message.getId());
		messageDTO.setIdAuthor(message.getAuthor().getId());
		messageDTO.setText(message.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		messageDTO.setDate(message.getDate().format(formatter));
		
		
		return Response.ok(messageDTO).build();
	}


	
	/**
	 * Delete message.
	 *
	 * @param id the id
	 * @return the response
	 */
	@DELETE
	@Path("/{id}")
	public Response deleteMessage(@PathParam("id") Long id) {
		Message message = messageDAO.find(id);
		if (message == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		messageDAO.delete(id);
		return Response.noContent().build();
	}

}
