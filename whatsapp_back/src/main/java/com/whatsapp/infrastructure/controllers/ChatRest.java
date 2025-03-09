package com.whatsapp.infrastructure.controllers;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.whatsapp.domain.dto.ChatDTO;
import com.whatsapp.domain.dto.MessageDTO;
import com.whatsapp.domain.entities.Chat;
import com.whatsapp.domain.entities.Message;
import com.whatsapp.domain.entities.User;
import com.whatsapp.infrastructure.repositories.ChatDAO;
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

@Path("/chats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChatRest {

	/** The chat DAO. */
	@Inject
	private ChatDAO chatDAO;

	/** The user DAO. */
	@Inject
	private UserDAO userDAO;

	/**
	 * Createchat.
	 *
	 * @param chat the chat
	 * @return the response
	 */
	@POST
	public Response createChat(ChatDTO chat) {
		if (chat.getUsers() == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Los usuarios son obligatorios").build();
		}

		Chat newChat = new Chat();

		try {
			UserDAO userDAO = new UserDAO();

			for (Long id : chat.getUsers()) {
				User chatUser = userDAO.findById(id);
				newChat.addUser(chatUser);
			}
		} catch (Exception e) {
			System.out.println("User " + e.getMessage());
		}

		chatDAO.save(newChat);
		return Response.status(Response.Status.CREATED).entity(newChat).build();
	}

	/**
	 * Gets the chat.
	 *
	 * @param id the id
	 * @return the chat
	 */
	@GET
	@Path("/{id}")
	public Response getChat(@PathParam("id") Long id) {
		Chat chat = chatDAO.find(id);
		if (chat == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		List<MessageDTO> messageList = new ArrayList();
		for (Message message : chat.getMessages()) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setChatId(message.getId());
			messageDTO.setIdAuthor(message.getAuthor().getId());
			messageDTO.setText(message.getText());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			messageDTO.setDate(message.getDate().format(formatter));
			messageList.add(messageDTO);
		}

		ChatDTO chatDTO = new ChatDTO();
		List<Long> idList = new ArrayList();
		for (User user : chat.getUsers()) {
			idList.add(user.getId());
		}
		chatDTO.setUsers(idList);
		chatDTO.setMessages(messageList);

		return Response.ok(chatDTO).build();
	}

	/**
	 * Gets the chatsby email.
	 *
	 * @param email the email
	 * @return the chatsby email
	 */
	@GET
	@Path("/email/{email}")
	public Response getChatsbyEmail(@PathParam("email") String email) {

		User puser = userDAO.findByEmail(email);
		if (puser == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		List<Chat>chat = chatDAO.findChatIdsByUserId(puser.getId());
		
		List<Chat> prevChats = new ArrayList<Chat>();
		
		for (Chat chat3 : chat) {
			prevChats.add(chatDAO.find(chat3.getId()));
		}
		
		if (chat == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		List<ChatDTO> finalChats = new ArrayList<>();
		for (Chat chat2 : chat) {
			List<MessageDTO> messageList = new ArrayList();
			for (Message message : chat2.getMessages()) {
				MessageDTO messageDTO = new MessageDTO();
				messageDTO.setChatId(message.getId());
				messageDTO.setIdAuthor(message.getAuthor().getId());
				messageDTO.setText(message.getText());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				messageDTO.setDate(message.getDate().format(formatter));
				messageList.add(messageDTO);
			}
			ChatDTO chatDTO = new ChatDTO();
			List<Long> idList = new ArrayList();
			for (User user : chat2.getUsers()) {
				idList.add(user.getId());
			}
			chatDTO.setUsers(idList);
			chatDTO.setMessages(messageList);
			
			finalChats.add(chatDTO);
		}

		return Response.ok(finalChats).build();
	}

	/**
	 * Deletechat.
	 *
	 * @param id the id
	 * @return the response
	 */
	@DELETE
	@Path("/{id}")
	public Response deleteChat(@PathParam("id") Long id) {
		Chat chat = chatDAO.find(id);
		if (chat == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		chatDAO.delete(id);
		return Response.noContent().build();
	}

}
