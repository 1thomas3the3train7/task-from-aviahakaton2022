package com.example.tasksher.RepositoryImpl;

import com.example.tasksher.Model.Flight;
import com.example.tasksher.Repository.FlightRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class FlightRepositoryImpl implements FlightRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(Flight flight) {
        em.persist(flight);
    }

    @Override
    @Transactional
    public void delete(Flight flight) {
        em.remove(em.contains(flight) ? flight : em.merge(flight));
    }

    @Override
    @Transactional
    public List<Flight> getFlightTypeA() {
        List<Flight> flightsA = em.createQuery("SELECT f FROM Flight f where f.completed = false " +
                "and f.performed = false and f.type = 'A' and f.countTime = " +
                "(select min(ff.countTime) from Flight ff where ff.completed = false " +
                "and ff.performed = false and ff.type = 'A')", Flight.class).getResultList();
        return flightsA;
    }

    @Override
    @Transactional
    public List<Flight> getFlightTypeB() {
        List<Flight> flightsB = em.createQuery("select f from Flight f where f.type = 'D' " +
                "and f.performed = false and f.completed = false and f.countTime = " +
                "(select min(ff.countTime) from Flight ff where ff.completed = false and ff.performed = false " +
                "and ff.type = 'D')", Flight.class).getResultList();
        return flightsB;
    }

    @Override
    @Transactional
    public Flight getFlightWithMinTime() {
        Flight flightsA = em.createQuery("SELECT f FROM Flight f where f.completed = false " +
                "and f.performed = false and f.type = 'A' and f.countTime = " +
                "(select min(ff.countTime) from Flight ff where ff.completed = false " +
                "and ff.performed = false and ff.type = 'A')", Flight.class)
                .getResultList().stream().findFirst().orElse(null);

        Flight flightsB = em.createQuery("select f from Flight f where f.type = 'D' " +
                "and f.performed = false and f.completed = false and f.countTime = " +
                "(select min(ff.countTime) from Flight ff where ff.completed = false and ff.performed = false " +
                "and ff.type = 'D')", Flight.class).getResultList().stream().findFirst().orElse(null);
        if(flightsA == null && flightsB == null){
            //TODO handler exc
            throw new RuntimeException("not found flight");
        }
        if(flightsA == null){
            return flightsB;
        }
        if(flightsB == null){
            return flightsA;
        }
        if(flightsB.getCountTime() - 32 <= flightsA.getCountTime()){
            return flightsB;
        }
        return flightsA;
    }
}
