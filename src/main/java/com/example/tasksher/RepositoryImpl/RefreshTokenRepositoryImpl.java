package com.example.tasksher.RepositoryImpl;

import com.example.tasksher.Model.RefreshToken;
import com.example.tasksher.Repository.RefreshTokenRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(RefreshToken refreshToken) {
        em.persist(refreshToken);
    }

    @Override
    @Transactional
    public void delete(RefreshToken refreshToken) {
        em.remove(em.contains(refreshToken) ? refreshToken : em.merge(refreshToken));
    }
}
