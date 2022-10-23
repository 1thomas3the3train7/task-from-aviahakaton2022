package com.example.tasksher.RepositoryImpl;

import com.example.tasksher.Model.BusType2;
import com.example.tasksher.Repository.BusType2Repository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BusType2RepositoryImpl implements BusType2Repository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(BusType2 busType2) {
        em.persist(busType2);
    }

    @Override
    @Transactional
    public void delete(BusType2 busType2) {
        em.remove(em.contains(busType2) ? busType2 : em.merge(busType2));
    }

    @Override
    @Transactional
    public List<BusType2> getNotMoveBus() {
        final List<BusType2> busType2s = em.createQuery("SELECT b FROM BusType2 b JOIN FETCH b.point " +
                "WHERE b.move = false ",BusType2.class).getResultList();
        return busType2s;
    }
}
