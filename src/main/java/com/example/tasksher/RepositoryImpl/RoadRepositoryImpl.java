package com.example.tasksher.RepositoryImpl;

import com.example.tasksher.Model.Road;
import com.example.tasksher.Repository.RoadRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RoadRepositoryImpl implements RoadRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(Road road) {
        em.persist(road);
    }

    @Override
    @Transactional
    public void delete(Road road) {
        em.remove(em.contains(road) ? road : em.merge(road));
    }
    @Override
    @Transactional
    public List<Road> getRoadsBySourcePoint(final int id){
        List<Road> roads = em.createQuery("select r from Road r where r.source_point_id = ?1", Road.class)
                .setParameter(1,id)
                .getResultList();
        return roads;
    }
}
