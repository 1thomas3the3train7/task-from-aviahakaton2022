package com.example.tasksher.Service;

import com.example.tasksher.Model.BusType1;
import com.example.tasksher.Model.BusType2;
import com.example.tasksher.Model.Flight;
import com.example.tasksher.Model.Point;
import com.example.tasksher.Repository.BusType1Repository;
import com.example.tasksher.Repository.BusType2Repository;
import com.example.tasksher.Repository.FlightRepository;
import com.example.tasksher.Repository.PointRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final BusType2Repository busType2Repository;
    private final BusType1Repository busType1Repository;
    private final PointRepository pointRepository;
    private final WayService wayService;

    public FlightService(FlightRepository flightRepository, BusType2Repository busType2Repository,
                         BusType1Repository busType1Repository, PointRepository pointRepository, WayService wayService) {
        this.flightRepository = flightRepository;
        this.busType2Repository = busType2Repository;
        this.busType1Repository = busType1Repository;
        this.pointRepository = pointRepository;
        this.wayService = wayService;
    }

    public List<Flight> getFlightA(){
        return flightRepository.getFlightTypeA();
    }
    public void setBusToFlights(){
        Flight flight = flightRepository.getFlightWithMinTime();
        final int countPas = flight.getCountPassenger();
        final String token = UUID.randomUUID().toString();
        List<Integer> integers = new ArrayList<>();
        final List<BusType2> busType2s = busType2Repository.getNotMoveBus();
        final List<BusType1> busType1s = busType1Repository.getNotMoveBus();
        if(flight.getType().equals("A")){
            if(busType2s.size() > 0){
                int time = 0;

                List<BusType2> startBus = new ArrayList<>();
                for(BusType2 b2 : busType2s){
                    Point px1 = b2.getPoint();
                    if(px1 != null){
                        List<Integer> integers1 = new ArrayList<>();
                        int dist = 0;
                        String token1 = UUID.randomUUID().toString();
                        wayService.check(px1.getPoint_id(),integers1,dist,flight.getNumberFlight(),token,b2.getId());
                    }else{
                        startBus.add(b2);
                    }
                }
                if(startBus.size() > 0){
                    int count1 = countPas;
                    if(startBus.size() * 50 > countPas){
                        for(BusType2 b : startBus){
                            if(count1 < 0){
                                break;
                            }
                            Point point = pointRepository.getPointByLocationId(flight.getNumberParkingPlace());
                            b.setPoint(point);
                            count1 = count1 - 50;
                        }
                    } else {
                        for(BusType2 b : startBus){
                            if(count1 < 0){
                                break;
                            }
                            Point point = pointRepository.getPointByLocationId(flight.getNumberParkingPlace());
                            b.setPoint(point);
                            count1 = count1 - 50;
                        }

                    }
                }
                if(countPas <= 100){
                    BusType1 busType1 = busType1s.get(0);

                }
            }
        }


        int dist = 0;
        if(flight.getType().equals("D")){
            Point p1 = pointRepository.getPointByLocationId(flight.getNumberParkingPlace());
            Point p2 = pointRepository.getPointByLocationId(flight.getNumberGate());
            wayService.check(p1.getPoint_id(),integers,dist,p2.getPoint_id(),token);
        } else {
            Point p1 = pointRepository.getPointByLocationId(flight.getNumberParkingPlace());
            Point p2 = pointRepository.getPointByLocationId(flight.getNumberGate());
            wayService.check(p2.getPoint_id(),integers,dist,p1.getPoint_id(),token);
        }

    }
}
