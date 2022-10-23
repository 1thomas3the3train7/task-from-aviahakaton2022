package com.example.tasksher.RepositoryImpl;

import com.example.tasksher.Model.User;
import com.example.tasksher.Repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    @Transactional
    public User getUserAndRoleAndTokenByEmail(final String email) {
        final User user = em.createQuery("SELECT u" +
                        " FROM User u JOIN FETCH u.roles " +
                        "JOIN FETCH u.token WHERE u.email = ?1", User.class)
                .setParameter(1,email)
                .getResultList().stream().findFirst().orElse(null);
        return user;
    }
}
