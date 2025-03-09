package com.whatsapp.infrastructure.controllers;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.whatsapp.domain.dto.UserDTO;
import com.whatsapp.domain.entities.User;
import com.whatsapp.infrastructure.repositories.LoginRequest;
import com.whatsapp.infrastructure.repositories.UserDAO;
import com.whatsapp.infrastructure.security.AuthService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRest {

	/** The user DAO. */
	@Inject
	private UserDAO userDAO;
	
    /** The auth service. */
    @Inject
    private AuthService authService;

	/**
	 * Handle options.
	 *
	 * @return the response
	 */
	@OPTIONS
	@Produces("application/json")
	public Response handleOptions() {
		return Response.ok().header("Access-Control-Allow-Origin", "http://localhost:4200")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
				.header("Access-Control-Allow-Credentials", "true").build();
	}

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the response
	 */
	@POST
	public Response createUser(@RequestBody UserDTO user) {
		if (user.getName() == null || user.getEmail() == null || user.getPass() == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("El nombre, email y contraseña son obligatorios")
					.build();
		}

		// Verificar si el email ya existe
		if (userDAO.findByEmail(user.getEmail()) != null) {
			return Response.status(Response.Status.CONFLICT) // 409 Conflict
					.entity("El email ya está en uso").build();
		}

		// Verificar si el email ya existe
		if (userDAO.findByUser(user.getName()) != null) {
			return Response.status(Response.Status.CONFLICT) // 409 Conflict
					.entity("El usuario ya está en uso").build();
		}

		User newUser = new User();
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setPass(user.getPass());
		newUser.setPhoto(user.getPhoto());

		userDAO.save(newUser);
		return Response.status(Response.Status.OK).entity(user).build();
	}

	/**
	 * Login.
	 *
	 * @param request the request
	 * @return the response
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest request) {
		boolean isValid = authService.verifyUser(request.getEmail(), request.getPass());
		if (isValid) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
		}
	}

	/**
	 * Gets the user.
	 *
	 * @param id the id
	 * @return the user
	 */
	@GET
	@Path("/{id}")
	public Response getUserbyId(@PathParam("id") Long id) {
		User user = userDAO.findById(id);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(user).build();
	}

	/**
	 * Gets the user.
	 *
	 * @param email the email
	 * @return the user
	 */
	@GET
	@Path("/email/{email}")
	public Response getUserbyEmail(@PathParam("email") String email) {
		User user = userDAO.findByEmail(email);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(user).build();
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@GET
	public Response getAllUsers() {
		return Response.ok(userDAO.findAll()).build();
	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @return the response
	 */
	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") Long id) {
		User user = userDAO.findById(id);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		userDAO.delete(id);
		return Response.noContent().build();
	}

}
