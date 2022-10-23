package com.example.tasksher.RepositoryImpl;

import com.example.tasksher.Model.Role;
import com.example.tasksher.Repository.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(Role role) {
        em.persist(role);
    }

    @Override
    @Transactional
    public void delete(Role role) {
        em.remove(em.contains(role) ? role : em.merge(role));
    }
}
