package com.whatsapp.infrastructure.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.whatsapp.domain.dto.MessageDTO;
import com.whatsapp.domain.entities.Message;
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

	/**
	 * Creates the message.
	 *
	 * @param message the message
	 * @return the response
	 */
	@POST
	public Response createMessage(MessageDTO message) {
		if (message.getIdAuthor() == null || message.getText() == null || message.getDate() == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("El autor, la fecha y el mensaje son obligatorios").build();
		}

		Message newMessage = new Message();

		newMessage.setText(message.getText());
		newMessage.setAuthor(userDAO.findById(message.getIdAuthor()));
		newMessage.setDate(LocalDateTime.now());
		newMessage.setDestino(message.getDest());

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

		messageDTO.setDest(message.getDestino());
		messageDTO.setIdAuthor(message.getAuthor().getId());
		messageDTO.setText(message.getText());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		messageDTO.setDate(message.getDate().format(formatter));

		return Response.ok(messageDTO).build();
	}

	/**
	 * Gets the message.
	 *
	 * @param id the id
	 * @return the message
	 */
	@GET
	@Path("/conversation/{ids}")
	public Response getMessage(@PathParam("ids") String ids) {

	    String[] parts = ids.split(",");
	    if(parts.length != 2){
	        return Response.status(Response.Status.BAD_REQUEST)
	                .entity("Deben proporcionarse exactamente 2 IDs separados por coma.").build();
	    }

	    Long id1;
	    Long id2;

	    try {
	        id1 = Long.parseLong(parts[0]);
	        id2 = Long.parseLong(parts[1]);
	    } catch(NumberFormatException ex){
	        return Response.status(Response.Status.BAD_REQUEST)
	                .entity("IDs inválidos, deben ser numéricos.").build();
	    }

	    List<Message> messages = messageDAO.findByUsers(id1, id2);
	    if (messages == null || messages.isEmpty()) {
	        return Response.status(Response.Status.NOT_FOUND).build();
	    }

	    List<MessageDTO> listMessageDTO = new ArrayList<>();

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	    for (Message msg : messages) {
	        MessageDTO messageDTO = new MessageDTO();
	        messageDTO.setDest(msg.getDestino());
	        messageDTO.setIdAuthor(msg.getAuthor().getId());
	        messageDTO.setText(msg.getText());
	        messageDTO.setDate(msg.getDate().format(formatter));

	        listMessageDTO.add(messageDTO);
	    }

	    return Response.ok(listMessageDTO).build();
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
