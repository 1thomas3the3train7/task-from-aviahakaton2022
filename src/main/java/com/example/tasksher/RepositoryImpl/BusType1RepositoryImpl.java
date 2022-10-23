package com.example.tasksher.RepositoryImpl;

import com.example.tasksher.Model.BusType1;
import com.example.tasksher.Repository.BusType1Repository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BusType1RepositoryImpl implements BusType1Repository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(BusType1 busType1) {
        em.persist(busType1);
    }

    @Override
    @Transactional
    public void delete(BusType1 busType1) {
        em.remove(em.contains(busType1) ? busType1 : em.merge(busType1));
    }

    @Override
    @Transactional
    public List<BusType1> getNotMoveBus() {
        final List<BusType1> busType1s = em.createQuery("SELECT b FROM BusType2 b JOIN FETCH b.point " +
                "WHERE b.move = false",BusType1.class).getResultList();
        return busType1s;
    }
}
