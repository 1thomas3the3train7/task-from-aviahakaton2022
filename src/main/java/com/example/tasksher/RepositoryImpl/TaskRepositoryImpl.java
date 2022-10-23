package com.example.tasksher.RepositoryImpl;

import com.example.tasksher.Model.Task;
import com.example.tasksher.Repository.TaskRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(Task task) {
        em.persist(task);
    }

    @Override
    @Transactional
    public void delete(Task task) {
        em.remove(em.contains(task) ? task : em.merge(task));
    }

    @Override
    @Transactional
    public Task getTaskByToken(String token) {
        Task task = em.createQuery("SELECT t FROM Task t WHERE t.token = ?1",Task.class)
                .setParameter(1,token)
                .getResultList().stream().findFirst().orElse(null);
        return task;
    }
}
