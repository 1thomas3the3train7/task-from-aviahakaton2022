package com.example.tasksher.RepositoryImpl;

import com.example.tasksher.Model.Point;
import com.example.tasksher.Repository.PointRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class PointRepositoryImpl implements PointRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(Point point) {
        em.persist(point);
    }

    @Override
    @Transactional
    public void delete(Point point) {
        em.remove(em.contains(point) ? point : em.merge(point));
    }

    @Override
    @Transactional
    public Point getPointByLocationId(String location_id) {
        final Point point = em.createQuery("select p from Point p where p.location_id = ?1", Point.class)
                .setParameter(1,location_id)
                .getResultList().stream().findFirst().orElse(null);
        return point;
    }
}
