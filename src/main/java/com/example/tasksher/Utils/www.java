package com.example.tasksher.Utils;

import com.example.tasksher.Model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class www {
    /*List<Flight> flight = em.createQuery("SELECT f FROM Flight f where f.completed = false " +
            "and f.performed = false and f.countTime = " +
            "(select max(ff.countTime) from Flight ff " +
            "where ff.completed = false and f.performed = false )", Flight.class).getResultList();
    List<BusType1> busType1s = em.createQuery("SELECT b FROM BusType1 b WHERE b.move = false", BusType1.class)
            .getResultList();
    List<BusType2> busType2s = em.createQuery("SELECT b FROM  BusType2 b WHERE b.move = false ", BusType2.class)
            .getResultList();
    LocalDateTime localDateTime = LocalDateTime.now();
    final int countTimeNow = localDateTime.getHour() * 60 + localDateTime.getMinute();

    List<Flight> flightsA = em.createQuery("SELECT f FROM Flight f where f.completed = false " +
            "and f.performed = false and f.type = 'A' and f.countTime = " +
            "(select min(ff.countTime) from Flight ff where ff.completed = false " +
            "and ff.performed = false and ff.type = 'A')", Flight.class).getResultList();

    List<Flight> flightsB = em.createQuery("select f from Flight f where f.type = 'D' " +
            "and f.performed = false and f.completed = false and f.countTime = " +
            "(select min(ff.countTime) from Flight ff where ff.completed = false and ff.performed = false " +
            "and ff.type = 'D')", Flight.class).getResultList();
		flightsA.forEach(f -> {
        String point1 = f.getNumberParkingPlace();
        String point2 = f.getNumberGate();
        boolean active = true;
        Point p1 = em.createQuery("select p from Point p where p.location_id = ?1", Point.class)
                .setParameter(1,point1)
                .getSingleResult();
        Point p2 = em.createQuery("select p from Point p where p.location_id = ?1", Point.class)
                .setParameter(1,point2)
                .getSingleResult();
    });
    Flight flight1 = flightsA.stream().findFirst().orElse(null);
    int matrix[][] = new int[857][857];
    int matrix1[][] = new int[857][857];
		*//*Point point1 = em.createQuery("select p from Point p where p.point_id = ?1", Point.class)
				.setParameter(1,1092)
				.getResultList().stream().findFirst().orElse(null);
		System.out.println(point1);
		List<Road> road = em.createQuery("select r from Road r where r.target_point_id = 1092", Road.class)
				.getResultList();
		System.out.println(road);*//*
		for(int i = 0;i < 857;i++){
        System.out.println(i + 1);
        Point point = em.createQuery("select p from Point p where p.id = ?1", Point.class)
                .setParameter(1,Long.parseLong(String.valueOf(i + 1)))
                .getSingleResult();
        List<Road> roads = em.createQuery("select r from Road r where r.source_point_id = ?1", Road.class)
                .setParameter(1,point.getPoint_id())
                .getResultList();
        List<Integer> int2 = new ArrayList<>();
        List<Integer> introad = new ArrayList<>();
        List<Integer> intId = new ArrayList<>();
        Map<Integer,Road> map = new HashMap<>();
        roads.forEach(r -> {
            if(map.containsKey(r.getTarget_point_id())){
                if(r.getDistance() <= map.get(r.getTarget_point_id()).getDistance()){
                    map.remove(r.getTarget_point_id());
                    map.put(r.getTarget_point_id(),r);
                }
            } else {
                map.put(r.getTarget_point_id(),r);
            }
        });
        List<Road> roadList = new ArrayList<>();
        map.forEach((c,r) -> {
            roadList.add(r);
        });
        roadList.forEach(r -> {
            System.out.println(r.getTarget_point_id());
            List<Point> point1 = em.createQuery("select p from Point p where p.point_id = ?1", Point.class)
                    .setParameter(1,r.getTarget_point_id())
                    .getResultList();
            point1.forEach(p -> intId.add(p.getPoint_id()));
            introad.add(r.getTarget_point_id());
        });
        for(int j = 0;j < 857;j++){
            matrix[i][j] = Integer.MAX_VALUE/2;
            if(introad.contains(j)){
                for(Road r : roads){
                    if(r.getTarget_point_id() == j){
                        System.out.println(r.getDistance());
                        matrix[i][j] = r.getDistance();
                    }
                }
            }
            if(intId.contains(j)){
                matrix1[i][j] = j;
            }
        }
    }
		for(int i = 0;i < 857;i++){
        for(int j = 0;j < 857;j++){
            if(matrix[i][j] != Integer.MAX_VALUE/2){
                for(int k = 0;k < 857;k++){
                    if(matrix[i][k] > matrix[i][j] + matrix[j][k]){
                        matrix[i][k] = matrix[i][j] + matrix[j][k];
                        matrix1[i][k] = matrix1[i][j];
                    }
                }
            }
        }
    }
		for(int i =0;i < 857;i++){
        for(int j = 0;j<857;j++){
            if(matrix[i][j] != Integer.MAX_VALUE/2){
                System.out.println(i);
                System.out.println(j);
                System.out.println(matrix[i][j]);
                System.out.println("endif");
            }
        }
    }
    Point p1 = em.createQuery("select p from Point p where p.location_id = ?1", Point.class)
            .setParameter(1,flight1.getNumberParkingPlace())
            .getSingleResult();
    Point p2 = em.createQuery("select p from Point p where p.location_id = ?1", Point.class)
            .setParameter(1,flight1.getNumberGate())
            .getSingleResult();
		System.out.println(p1.getPoint_id());
		System.out.println(p2.getPoint_id());
		System.out.println(matrix[Integer.parseInt(String.valueOf(p1.getId()))][Integer.parseInt(String.valueOf(p2.getId()))]);
		System.out.println(flightsA);*/
}
