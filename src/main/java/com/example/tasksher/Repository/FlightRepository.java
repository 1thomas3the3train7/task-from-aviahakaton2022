package com.example.tasksher.Repository;

import com.example.tasksher.Model.Flight;

import java.util.List;

public interface FlightRepository {
    void save(Flight flight);
    void delete(Flight flight);
    List<Flight> getFlightTypeA();
    List<Flight> getFlightTypeB();
    Flight getFlightWithMinTime();
}
