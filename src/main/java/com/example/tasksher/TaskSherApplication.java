package com.example.tasksher;

import com.example.tasksher.Model.*;
import com.example.tasksher.RepositoryImpl.UserRepositoryImpl;
import com.example.tasksher.Service.ParseXlsx;
import com.example.tasksher.Service.WayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TaskSherApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TaskSherApplication.class, args);
	}
	@Autowired
	private ParseXlsx parseXlsx;
	@Autowired
	private UserRepositoryImpl userRepository;
	@Autowired
	private WayService wayService;
	@PersistenceContext
	private EntityManager em;

	@Override
	public void run(String... args) throws Exception {
		parseXlsx.ParseFlights();
		parseXlsx.ParseSheetRoad();
		parseXlsx.ParseSheetPoint();

		List<Integer> integers = new ArrayList<>();
		int dist = 0;
		String token = "asdad";
		System.out.println("start");
		wayService.check(935,integers,dist,159,token);
		System.out.println("end");
		/*List<Flight> flightsA = em.createQuery("SELECT f FROM Flight f where f.completed = false " +
				"and f.performed = false and f.type = 'A' and f.countTime = " +
				"(select min(ff.countTime) from Flight ff where ff.completed = false " +
				"and ff.performed = false and ff.type = 'A')", Flight.class).getResultList();
		flightsA.forEach(f -> {
			List<Integer> integers = new ArrayList<>();
			int dist = 0;
			Point p1 = em.createQuery("select p from Point p where p.location_id = ?1", Point.class)
					.setParameter(1,f.getNumberParkingPlace())
					.getSingleResult();
			Point p2 = em.createQuery("select p from Point p where p.location_id = ?1", Point.class)
					.setParameter(1,f.getNumberGate())
					.getSingleResult();
			wayService.check(p1.getPoint_id(),integers,dist,p2.getPoint_id());
		});
		List<Flight> flightsB = em.createQuery("select f from Flight f where f.type = 'D' " +
				"and f.performed = false and f.completed = false and f.countTime = " +
				"(select min(ff.countTime) from Flight ff where ff.completed = false and ff.performed = false " +
				"and ff.type = 'D')", Flight.class).getResultList();
		flightsB.forEach(f -> {
			List<Integer> integers = new ArrayList<>();
			int dist = 0;
			Point p1 = em.createQuery("select p from Point p where p.location_id = ?1", Point.class)
					.setParameter(1,f.getNumberGate())
					.getSingleResult();
			Point p2 = em.createQuery("select p from Point p where p.location_id = ?1", Point.class)
					.setParameter(1,f.getNumberParkingPlace())
					.getSingleResult();
			wayService.check(p1.getPoint_id(),integers,dist,p2.getPoint_id());
		});*/

	}

}
