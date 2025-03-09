package com.whatsapp.infrastructure.security;

import com.whatsapp.domain.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class AuthService {

    @PersistenceContext(unitName = "whatsappPU")
    private EntityManager em;

    public boolean verifyUser(String email, String password) {
        try {
            User user = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return user.getPass().equals(password);
        } catch (NoResultException e) {
            return false;
        }
    }
}
